package uz.unicorn.rentme.criteria;

import lombok.*;
import uz.unicorn.rentme.criteria.base.AbstractCriteria;
import uz.unicorn.rentme.criteria.base.BaseCriteria;
import uz.unicorn.rentme.dto.DateSearchDTO;
import uz.unicorn.rentme.dto.location.LocationSearchDTO;
import uz.unicorn.rentme.dto.price.PriceSearchDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;
import uz.unicorn.rentme.enums.transport.TransportColor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCriteria extends AbstractCriteria implements BaseCriteria {
    private LocationSearchDTO location;
    private AdvertisementCategory category;
    private String model;
    private Integer year;
    private PriceSearchDTO price;
    private DateSearchDTO date;
    private List<TransportColor> colors;
}