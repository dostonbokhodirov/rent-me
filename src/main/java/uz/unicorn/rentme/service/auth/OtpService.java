package uz.unicorn.rentme.service.auth;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.unicorn.rentme.dto.auth.SmsSenderDTO;
import uz.unicorn.rentme.entity.Otp;
import uz.unicorn.rentme.exceptions.NotFoundException;
import uz.unicorn.rentme.repository.OtpRepository;
import uz.unicorn.rentme.response.AppErrorDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.BaseService;
import uz.unicorn.rentme.utils.OtpUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OtpService implements BaseService {

    private final OtpRepository otpRepository;
    private final OtpUtils otpUtils;

    public Otp getByPhoneNumber(String phoneNumber) {
        return otpRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new NotFoundException("Otp not found"));
    }

    public ResponseEntity<DataDTO<String>> sendSms(String phoneNumber) {
        try {
//            int random = otpUtils.randomCode();
            int random = 1;
            String jsonInputString = (new Gson()).toJson(
                    new SmsSenderDTO(phoneNumber,
                            "RentMe", "Hello, your code is: %d\nDon't share anyone".formatted(random)));
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(otpUtils.getBaseUrl()))
                    .header("Authorization", otpUtils.getAuthorization())
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                    .build();
            var client = java.net.http.HttpClient.newHttpClient();
            client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
            Optional<Otp> byPhoneNumber = otpRepository.findByPhoneNumber(phoneNumber);
            if (byPhoneNumber.isPresent()) {
                Otp otp = byPhoneNumber.get();
                otp.setCode(random);
                otp.setExpiry(LocalDateTime.now().plusMinutes(otpUtils.getExpiry()));
                otpRepository.save(otp);
                return new ResponseEntity<>(new DataDTO<>("success"));
            }
            otpRepository.save(new Otp(phoneNumber, LocalDateTime.now().plusMinutes(otpUtils.getExpiry()), random));
            return new ResponseEntity<>(new DataDTO<>("success"));
        } catch (IOException | InterruptedException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .message(e.getLocalizedMessage())
                    .message(e.getMessage())
                    .status(HttpStatus.CONFLICT)
                    .build()), HttpStatus.CONFLICT);
        }

    }

}
