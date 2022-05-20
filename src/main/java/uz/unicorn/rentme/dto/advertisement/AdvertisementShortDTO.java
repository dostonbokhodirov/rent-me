package uz.unicorn.rentme.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.dto.transport.TransportDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementShortDTO extends GenericDTO {
    private String description;
    private Long price;
    private String picture;
    private AdvertisementCategory category;
    private TransportDTO transportDTO;
}
