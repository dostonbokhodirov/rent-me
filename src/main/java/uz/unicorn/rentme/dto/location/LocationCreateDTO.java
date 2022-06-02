package uz.unicorn.rentme.dto.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.dto.base.BaseDTO;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationCreateDTO implements BaseDTO {

    @NotBlank(message = "Location name cannot be blank")
    private String name;

    @NotBlank(message = "Location longitude cannot be blank")
    private Double longitude;

    @NotBlank(message = "Location latitude cannot be blank")
    private Double latitude;

}
