package uz.unicorn.rentme.dto.transport;

import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.dto.picture.PictureCreateDto;
import uz.unicorn.rentme.enums.transport.TransportColor;
import uz.unicorn.rentme.enums.transport.TransportFuel;
import uz.unicorn.rentme.enums.transport.TransportTransmission;
import uz.unicorn.rentme.enums.transport.TransportType;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportCreateDTO implements BaseDTO {

    @NotBlank
    private TransportType type;

    @NotBlank
    private String model;

    @NotBlank
    @Digits(integer = Integer.MAX_VALUE, fraction = 0)
    @Pattern(regexp = "\\d{4}")
    private Integer year;

    @NotBlank
    private TransportTransmission transmission;

    @NotBlank
    private TransportFuel fuelType;

    @NotBlank
    private TransportColor color;

    @NotBlank
    private List<PictureCreateDto> pictures;

    @NotBlank
    private Boolean wellEquipped = false;
}
