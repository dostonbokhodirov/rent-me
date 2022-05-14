package uz.unicorn.rentme.dto.auth;

import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserCreateDTO implements BaseDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
