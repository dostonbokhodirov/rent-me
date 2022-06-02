package uz.unicorn.rentme.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.unicorn.rentme.criteria.AdvertisementCriteria;
import uz.unicorn.rentme.dto.MainPageDTO;
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
    public ResponseEntity<DataDTO<MainPageDTO>> mainPage(@RequestBody Integer size) {
        AdvertisementCriteria advertisementCriteria = new AdvertisementCriteria(size, 0);
        List<String> pathList = List.of(
                "https://firebasestorage.googleapis.com/v0/b/picturesaver-61bc7.appspot.com/o/0e641539-a158-4cb0-8b38-b7088439b627.jpg?alt=media",
                "https://firebasestorage.googleapis.com/v0/b/picturesaver-61bc7.appspot.com/o/c97faef1-f42e-4328-b44b-4a7d80c75c3e.jpg?alt=media",
                "https://firebasestorage.googleapis.com/v0/b/picturesaver-61bc7.appspot.com/o/9a72e5cd-8276-432b-b3db-ba6d58beeea0.jpg?alt=media",
                "https://firebasestorage.googleapis.com/v0/b/picturesaver-61bc7.appspot.com/o/459467e0-fae8-472c-bb05-e330e7db1486.jpg?alt=media",
                "https://firebasestorage.googleapis.com/v0/b/picturesaver-61bc7.appspot.com/o/0764f9e0-6130-4f9a-9770-2346e20cc224.jpg?alt=media",
                "https://firebasestorage.googleapis.com/v0/b/picturesaver-61bc7.appspot.com/o/6e8b3ffd-a616-4381-bcde-84e73bc297f6.jpg?alt=media"
        );
        MainPageDTO dto = MainPageDTO
                .builder()
                .picturePathList(pathList)
                .lastAdvertisements(advertisementService.getAllLast(advertisementCriteria).getData().getData())
                .dailyAdvertisements(advertisementService.getAllDaily(advertisementCriteria).getData().getData())
                .monthlyAdvertisements(advertisementService.getAllLongTerm(advertisementCriteria).getData().getData())
                .build();
        return new ResponseEntity<>(new DataDTO<>(dto));
    }

}
