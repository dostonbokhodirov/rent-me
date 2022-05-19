package uz.unicorn.rentme.controller;

import org.springframework.web.bind.annotation.*;
import uz.unicorn.rentme.controller.base.AbstractController;
import uz.unicorn.rentme.controller.base.GenericCrudController;
import uz.unicorn.rentme.criteria.AdvertisementCriteria;
import uz.unicorn.rentme.dto.advertisement.AdvertisementCreateDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementUpdateDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.AdvertisementService;

import java.util.List;

@RestController
@RequestMapping(value = "/advertisement")
public class AdvertisementController extends AbstractController<AdvertisementService>
        implements GenericCrudController<AdvertisementDTO, AdvertisementCreateDTO, AdvertisementUpdateDTO, AdvertisementCriteria> {

    public AdvertisementController(AdvertisementService service) {
        super(service);
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<DataDTO<AdvertisementDTO>> get(@PathVariable Long id) {
        return service.get(id);
    }

    @Override
    @PostMapping(value = "/list")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAll(AdvertisementCriteria criteria) {
        return service.getAll(criteria);
    }

    @GetMapping(value = "/list_my")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAllMyList( AdvertisementCriteria criteria) {
        return service.getAllMyList(criteria);
    }
    @GetMapping(value = "/list_my_save")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getMySave( AdvertisementCriteria criteria) {
        return service.getAllMySave(criteria);
    }

    @Override
    @PostMapping(value = "/create")
    public ResponseEntity<DataDTO<Long>> create(@RequestBody AdvertisementCreateDTO dto) {
        return service.create(dto);
    }

    @Override
    @PostMapping(value = "/update")
    public ResponseEntity<DataDTO<Long>> update(AdvertisementUpdateDTO dto) {
        return null;
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        return null;
    }
    @GetMapping("/list_daily")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> dailyAds(
            @RequestBody AdvertisementCriteria criteria
    ){
        return service.getDailyAdvertisement(criteria);
    }
}
