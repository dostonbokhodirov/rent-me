package uz.unicorn.rentme.dto.transportType;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportTypeDTO extends GenericDTO {
    private String name;
    private AdvertisementCategory category;
    private String imagePath;
}
