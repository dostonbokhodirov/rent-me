package uz.unicorn.rentme.dto.transportType;

import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.entity.Transport;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import java.util.List;

@AllArgsConstructor
@Builder
@Setter
@Getter
@NoArgsConstructor
public class TransportTypeCreateDTO implements BaseDTO {
    private String name;
    private AdvertisementCategory category;
    private String imagePath;
    private List<Transport> transports;
}
