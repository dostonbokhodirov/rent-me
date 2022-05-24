package uz.unicorn.rentme.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+\\d{12}(\\d{2})?$", message = "Phone number is invalid")
    private String phoneNumber;

    @NotBlank(message = "Phone number cannot be blank")
    private String code;
}
