package uz.unicorn.rentme.dto.transport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.j2objc.annotations.ObjectiveCName;
import lombok.*;
import org.springdoc.api.annotations.ParameterObject;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.dto.picture.PictureCreateDto;
import uz.unicorn.rentme.dto.picture.PictureDTO;
import uz.unicorn.rentme.enums.transport.TransportColor;
import uz.unicorn.rentme.enums.transport.TransportFuel;
import uz.unicorn.rentme.enums.transport.TransportTransmission;
import uz.unicorn.rentme.enums.transport.TransportType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportCreateDTO implements BaseDTO {

    private TransportType type;

    private String model;

    private Integer year;

    private TransportTransmission transmission;

    private TransportFuel fuelType;

    private TransportColor color;

    private List<PictureCreateDto> pictures;

    private Boolean wellEquipped;
}
