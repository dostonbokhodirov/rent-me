package uz.unicorn.rentme.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.unicorn.rentme.dto.MainPageDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.FileService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MainController {

    private final FileService fileService;

    @PostMapping(value = "/main-page")
    public ResponseEntity<DataDTO<MainPageDTO>> mainPage() {



        return null;
    }

}
