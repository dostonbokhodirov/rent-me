package uz.unicorn.rentme.dto.transport;

import lombok.Getter;
import lombok.Setter;
import uz.unicorn.rentme.enums.transport.TransportColor;
import uz.unicorn.rentme.enums.transport.TransportFuel;
import uz.unicorn.rentme.enums.transport.TransportTransmission;
import uz.unicorn.rentme.enums.transport.TransportType;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class TransportDTO {

    private TransportType type;

    private String model;

    private Integer year;

    private TransportTransmission transmission;

    private TransportFuel fuelType;

    private TransportColor color;

    private List<PictureDTO> pictures;

    private Boolean wellEquipped;

}
