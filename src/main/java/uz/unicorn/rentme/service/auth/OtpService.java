package uz.unicorn.rentme.service.auth;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.unicorn.rentme.dto.auth.SmsSenderDTO;
import uz.unicorn.rentme.entity.AuthUser;
import uz.unicorn.rentme.entity.Otp;
import uz.unicorn.rentme.repository.AuthUserRepository;
import uz.unicorn.rentme.repository.OtpRepository;
import uz.unicorn.rentme.response.AppErrorDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.BaseService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OtpService implements BaseService {

    private final String baseUrl = "https://rest.messagebird.com/messages";
    private final String authorization = "AccessKey pX4Ti0YqeoTUeCzX9ezBFpWT1";
    private final int expiry = 10; //in minutes

    private final OtpRepository otpRepository;

    public ResponseEntity<DataDTO<String>> sendSms(String phoneNumber) {
        try {
            int random  = randomCode();
            String jsonInputString = (new Gson()).toJson(
                    new SmsSenderDTO(phoneNumber, "RentMe","Hello, your opt code is: " + random));

            var request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl))
                    .header("Authorization",authorization)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                    .build();

            var client = java.net.http.HttpClient.newHttpClient();
            var response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());

            Optional<Otp> byPhoneNumber = otpRepository.findByPhoneNumber(phoneNumber);
            System.out.println("sendSMS statusCode: "+response.statusCode());
            System.out.println("sendSMS body: "+response.body());
            //v1 =>  statusCode == 200
            if (byPhoneNumber.isPresent()){
                Otp otp = byPhoneNumber.get();
                otp.setCode(random);
                otp.setExpiry(LocalDateTime.now().plusMinutes(expiry));
                otpRepository.save(otp);
                return new ResponseEntity<>(new DataDTO<>("success"), HttpStatus.OK);
            }
            otpRepository.save(new Otp(phoneNumber, LocalDateTime.now().plusMinutes(expiry) ,random));
            // v2 =>  statusCode != 200

            return new ResponseEntity<>(new DataDTO<>("success"), HttpStatus.OK);

        } catch (MalformedURLException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .message(e.getLocalizedMessage())
                    .message(e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .message(e.getLocalizedMessage())
                    .message(e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()), HttpStatus.OK);
        } catch (InterruptedException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .message(e.getLocalizedMessage())
                    .message(e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()), HttpStatus.OK);
        }

    }
    
    private int randomCode(){
        int max = 999999;
        int min = 900000;
        int range = max - min + 1;

        return (int)(Math.random() * range) + min;
    }

}
