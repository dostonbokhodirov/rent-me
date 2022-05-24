package uz.unicorn.rentme.criteria;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.Point;
import uz.unicorn.rentme.criteria.base.AbstractCriteria;
import uz.unicorn.rentme.enums.AdvertisementCategory;
import uz.unicorn.rentme.enums.transport.TransportColor;
import uz.unicorn.rentme.enums.transport.TransportFuel;
import uz.unicorn.rentme.enums.transport.TransportTransmission;
import uz.unicorn.rentme.enums.transport.TransportType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AdvertisementCriteria extends AbstractCriteria {

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


    @Builder
    public AdvertisementCriteria(Integer size, Integer page) {
        super(size, page);
    }

    @Builder(builderMethodName = "secondBuilder", buildMethodName = "secondBuild")
    public AdvertisementCriteria(Integer page) {
        super(page);
    }

    @Builder(builderMethodName = "searchBuilder", buildMethodName = "searchBuild")
    public AdvertisementCriteria(Long price, AdvertisementCategory category, Point location, LocalDateTime startDate, TransportType transportType, String transportModel, Integer transportYear, TransportTransmission transmission, TransportFuel transportFuel, TransportColor transportColor, Boolean wellEquipped) {
        this.price = price;
        this.category = category;
        this.location = location;
        this.startDate = startDate;
        this.transportType = transportType;
        this.transportModel = transportModel;
        this.transportYear = transportYear;
        this.transmission = transmission;
        this.transportFuel = transportFuel;
        this.transportColor = transportColor;
        this.wellEquipped = wellEquipped;
    }
}
