package uz.unicorn.rentme.dto.transportModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportModelCreateDTO implements BaseDTO {

    @NotBlank(message = "Transport model name cannot be blank")
    private String name;

    @NotBlank(message = "Transport category name cannot be blank")
    private AdvertisementCategory category;

    @NotBlank(message = "Transport image path name cannot be blank")
    private String imagePath;
    private String brand;
}
