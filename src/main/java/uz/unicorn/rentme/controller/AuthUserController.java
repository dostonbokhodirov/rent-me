package uz.unicorn.rentme.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import uz.unicorn.rentme.controller.base.AbstractController;
import uz.unicorn.rentme.controller.base.GenericCrudController;
import uz.unicorn.rentme.criteria.AuthUserCriteria;
import uz.unicorn.rentme.dto.auth.*;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.auth.AuthService;
import uz.unicorn.rentme.service.auth.AuthUserService;
import uz.unicorn.rentme.service.auth.OtpService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/auth")
public class AuthUserController extends AbstractController<AuthUserService>
        implements GenericCrudController<AuthUserDTO, AuthUserCreateDTO, AuthUserUpdateDTO, AuthUserCriteria> {

    private final AuthService authService;
    private final OtpService otpService;

    public AuthUserController(AuthUserService service, AuthService authService, OtpService otpService) {
        super(service);
        this.authService = authService;
        this.otpService = otpService;
    }

    @PostMapping(value = "/access-token")
    public ResponseEntity<DataDTO<SessionDTO>> getToken(@RequestBody LoginDTO dto) {
        return authService.getToken(dto);
    }

    @SneakyThrows
    @GetMapping(value = "/refresh-token")
    public ResponseEntity<DataDTO<SessionDTO>> getToken(HttpServletRequest request, HttpServletResponse response) {
        return authService.getRefreshToken(request, response);
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<DataDTO<AuthUserDTO>> get(@PathVariable Long id) {
        return service.get(id);
    }

    @Override
    @PostMapping(value = "/list")
    public ResponseEntity<DataDTO<List<AuthUserDTO>>> getAll(@RequestBody AuthUserCriteria criteria) {
        return null;
    }

    @Override
    @PostMapping(value = "/create")
    public ResponseEntity<DataDTO<Long>> create(AuthUserCreateDTO dto) {
        return service.create(dto);
    }

    @Override
    @PostMapping(value = "/update")
    public ResponseEntity<DataDTO<Long>> update(AuthUserUpdateDTO dto) {
        return null;
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DataDTO<Boolean>> delete(@PathVariable Long id) {
        return null;
    }

    @PostMapping(value = "/sendSms/{phoneNumber}")
    public ResponseEntity<DataDTO<String>> sendSms(@PathVariable String phoneNumber){
        return otpService.sendSms(phoneNumber);
    }
}
