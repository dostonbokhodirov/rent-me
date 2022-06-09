package uz.unicorn.rentme.dto;

import lombok.*;
import uz.unicorn.rentme.dto.advertisement.AdvertisementShortDTO;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.dto.brand.BrandShortDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainPageDTO implements BaseDTO {
    private List<String> pictures;
    private List<BrandShortDTO> brands;
    private List<AdvertisementShortDTO> lastAdvertisements;
    private List<AdvertisementShortDTO> dailyAdvertisements;
    private List<AdvertisementShortDTO> monthlyAdvertisements;
}
