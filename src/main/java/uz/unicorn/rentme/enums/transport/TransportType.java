package uz.unicorn.rentme.enums.transport;

import lombok.Getter;
import uz.unicorn.rentme.enums.AdvertisementCategory;

@Getter
public enum TransportType {
    NEXIA(AdvertisementCategory.CAR),
    COBALT(AdvertisementCategory.CAR),
    MALIBU(AdvertisementCategory.CAR),
    LACETTI(AdvertisementCategory.CAR),
    TRACKER(AdvertisementCategory.CAR),
    MATIZ(AdvertisementCategory.CAR),
    DAMAS(AdvertisementCategory.CAR),
    TRAILBLAZER(AdvertisementCategory.CAR),
    EPICA(AdvertisementCategory.CAR),
    BIKE(AdvertisementCategory.MOTORBIKE),
    SPARK(AdvertisementCategory.CAR),
    EQUINOX(AdvertisementCategory.CAR);
    private AdvertisementCategory category;

    TransportType(AdvertisementCategory category) {
        this.category = category;
    }
}
