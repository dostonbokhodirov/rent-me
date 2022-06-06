package uz.unicorn.rentme.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.dto.location.LocationDTO;
import uz.unicorn.rentme.dto.price.PriceDTO;
import uz.unicorn.rentme.dto.transport.TransportDTO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementDTO extends GenericDTO {
    private String description;
    private List<PriceDTO> prices;
    private String category;
    private LocationDTO location;
    private LocalDateTime startDate;
    private Long minDuration;
    private Long maxDuration;
    private TransportDTO transport;
    private LocalDateTime createdAt;
    private Long createdBy;
}
