package uz.unicorn.rentme.dto.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.dto.picture.PictureDTO;
import uz.unicorn.rentme.entity.TransportModel;
import uz.unicorn.rentme.enums.transport.TransportColor;
import uz.unicorn.rentme.enums.transport.TransportFuel;
import uz.unicorn.rentme.enums.transport.TransportTransmission;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransportDTO {

    @NotBlank(message = "Transport model can not be blank")
    private TransportModel type;

    @NotBlank(message = "Transport model can not be blank")
    private String model;

    @NotBlank(message = "Year can not be blank")
    @Pattern(regexp = "\\d{4}")
    private Integer year;

    @NotBlank(message = "Transport transmission can not be blank")
    private TransportTransmission transmission;

    @NotBlank(message = "Transport fuel can not be blank")
    private TransportFuel fuelType;

    @NotBlank(message = "Transport color can not be blank")
    private TransportColor color;

    @NotBlank(message = "Transport pictures can not be blank")
    private List<PictureDTO> pictures;

    private Boolean wellEquipped = false;

}
