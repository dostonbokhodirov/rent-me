package uz.unicorn.rentme.dto.transport;

import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.dto.picture.PictureCreateDto;
import uz.unicorn.rentme.dto.transportType.TransportTypeCreateDTO;
import uz.unicorn.rentme.enums.transport.TransportColor;
import uz.unicorn.rentme.enums.transport.TransportFuel;
import uz.unicorn.rentme.enums.transport.TransportTransmission;

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

    @NotBlank(message = "Transport type cannot be blank")
    private String transportType;

    @NotBlank(message = "Transport model cannot be blank")
    private String model;

    @NotBlank(message = "Transport year cannot be blank")
    @Pattern(regexp = "\\d{4}")
    private Integer year;

    @NotBlank(message = "Transport transmission cannot be blank")
    private TransportTransmission transmission;

    @NotBlank(message = "Transport fuel type cannot be blank")
    private TransportFuel fuelType;

    @NotBlank(message = "Transport color cannot be blank")
    private TransportColor color;

    @NotBlank(message = "Transport picture cannot be blank")
    private List<PictureCreateDto> pictures;

    private Boolean wellEquipped = false;
}
