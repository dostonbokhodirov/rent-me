package uz.unicorn.rentme.criteria;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.criteria.base.AbstractCriteria;

@Getter
@Setter
@NoArgsConstructor
public class AdvertisementCriteria extends AbstractCriteria {

    @Builder
    public AdvertisementCriteria(Integer size, Integer page) {
        super(size, page);
    }

    @Builder(builderMethodName = "secondBuilder", buildMethodName = "secondBuild")
    public AdvertisementCriteria(Integer page) {
        super(page);
    }
}
