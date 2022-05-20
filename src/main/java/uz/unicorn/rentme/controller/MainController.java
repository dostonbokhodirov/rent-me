package uz.unicorn.rentme.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.unicorn.rentme.criteria.AdvertisementCriteria;
import uz.unicorn.rentme.dto.MainPageDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementShortDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.AdvertisementService;
import uz.unicorn.rentme.service.FileService;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MainController {

    private final FileService fileService;
    private final AdvertisementService advertisementService;

    @PostMapping(value = "/main-page")
    public ResponseEntity<DataDTO<MainPageDTO>> mainPage() {
        AdvertisementCriteria advertisementCriteria = new AdvertisementCriteria(3, 0);

        List<String> pathList = List.of("path1", "path2", "path3");

        List<AdvertisementShortDTO> dailyAdvertisements = advertisementService.getAllDaily(advertisementCriteria).getData().getData();
        List<AdvertisementShortDTO> weeklyAdvertisements = advertisementService.getAllWeekly(advertisementCriteria).getData().getData();

        MainPageDTO dto = MainPageDTO
                .builder()
                .picturePathList(pathList)
                .lastAdvertisements(null)
                .dailyAdvertisements(dailyAdvertisements)
                .weeklyAdvertisements(weeklyAdvertisements)
                .build();

        return new ResponseEntity<>(new DataDTO<>(dto));
    }

}
