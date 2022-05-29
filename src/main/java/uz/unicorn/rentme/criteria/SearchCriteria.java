package uz.unicorn.rentme.criteria;

import lombok.*;
import org.springframework.data.geo.Point;
import uz.unicorn.rentme.criteria.base.BaseCriteria;
import uz.unicorn.rentme.dto.DateSearchDTO;
import uz.unicorn.rentme.dto.price.PriceSearchDTO;
import uz.unicorn.rentme.entity.TransportModel;
import uz.unicorn.rentme.enums.AdvertisementCategory;
import uz.unicorn.rentme.enums.transport.TransportColor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCriteria implements BaseCriteria {
    private Point location;
    private AdvertisementCategory category;
    private TransportModel model;
    private Integer year;
    private PriceSearchDTO price;
    private DateSearchDTO dates;
    private List<TransportColor> colors;
}