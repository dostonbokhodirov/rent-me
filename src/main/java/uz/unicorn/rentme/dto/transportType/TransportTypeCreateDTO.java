package uz.unicorn.rentme.dto.transportType;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.entity.Transport;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportTypeCreateDTO implements BaseDTO {
    private String name;
    private AdvertisementCategory category;
    private String imagePath;
}
