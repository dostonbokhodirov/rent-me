package uz.unicorn.rentme.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.dto.transport.TransportDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementShortDTO extends GenericDTO {

    @NotBlank
    private String description;

    @NotBlank
    @Digits(integer = Integer.MAX_VALUE, fraction = 0)
    @Pattern(regexp = "\\d+")
    private Long price;

    @NotBlank
    private String picture;

    @NotBlank
    private AdvertisementCategory category;

    @NotBlank
    private TransportDTO transportDTO;

}
