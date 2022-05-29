package uz.unicorn.rentme.dto.transportModel;

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
public class TransportModelDTO extends GenericDTO {
    private String name;
    private AdvertisementCategory category;
    private String imagePath;
}
