package uz.unicorn.rentme.dto.auth;

import lombok.*;
import org.springdoc.api.annotations.ParameterObject;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.enums.auth.Gender;
import uz.unicorn.rentme.enums.auth.Language;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject
public class AuthUserCreateDTO implements BaseDTO {

    @NotBlank
    @Pattern(regexp = "[A-Z]+")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "[A-Z]+")
    private String lastName;

    @NotBlank
//    @Pattern(regexp = "^\\+\\d{12}(\\d{2})?$", message = "Phone number is invalid")
    @Pattern(regexp = "[+](998)\\d{9}", message = "Phone number is invalid")
    private String phoneNumber;

    @NotBlank
    private Language language;

    @Email(regexp = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$", message = "Email is invalid")
    private String email = "example@gmail.com";

    @NotBlank
    private Gender gender;

}
