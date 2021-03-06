package uz.unicorn.rentme.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.dto.location.LocationUpdateDTO;
import uz.unicorn.rentme.dto.price.PriceDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementUpdateDTO extends GenericDTO {
    private String description;
    private List<PriceDTO> prices;
    private AdvertisementCategory category;
    private LocationUpdateDTO location;
    private LocalDateTime startDate;
    private Long minDuration;
    private Long maxDuration;
    private String transportType;
}
