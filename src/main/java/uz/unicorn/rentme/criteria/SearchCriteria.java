package uz.unicorn.rentme.criteria;

import lombok.*;
import org.springframework.data.geo.Point;
import uz.unicorn.rentme.criteria.base.BaseCriteria;
import uz.unicorn.rentme.enums.AdvertisementCategory;
import uz.unicorn.rentme.enums.transport.TransportColor;
import uz.unicorn.rentme.enums.transport.TransportFuel;
import uz.unicorn.rentme.enums.transport.TransportTransmission;
import uz.unicorn.rentme.enums.transport.TransportType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCriteria implements BaseCriteria {
    private Long price;
    private AdvertisementCategory category;
    private Point location;
    private LocalDateTime startDate;
    private TransportType transportType;
    private String transportModel;
    private Integer transportYear;
    private TransportTransmission transmission;
    private TransportFuel transportFuel;
    private TransportColor transportColor;
    private Boolean wellEquipped = false;
}
