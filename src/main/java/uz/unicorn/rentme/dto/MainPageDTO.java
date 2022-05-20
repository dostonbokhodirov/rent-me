package uz.unicorn.rentme.dto;

import lombok.*;
import uz.unicorn.rentme.dto.advertisement.AdvertisementDTO;
import uz.unicorn.rentme.dto.base.BaseDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainPageDTO implements BaseDTO {
    private List<String> picturePathList;
    private List<AdvertisementDTO> lastAdvertisements;
    private List<AdvertisementDTO> dailyAdvertisements;
    private List<AdvertisementDTO> weeklyAdvertisements;
}
