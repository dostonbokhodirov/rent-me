package uz.unicorn.rentme.controller;

import org.springframework.web.bind.annotation.*;
import uz.unicorn.rentme.controller.base.AbstractController;
import uz.unicorn.rentme.controller.base.GenericCrudController;
import uz.unicorn.rentme.criteria.AdvertisementCriteria;
import uz.unicorn.rentme.criteria.SearchCriteria;
import uz.unicorn.rentme.dto.advertisement.AdvertisementCreateDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementShortDTO;
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
    @GetMapping(value = "/list")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAll(AdvertisementCriteria criteria) {
        return service.getAll(criteria);
    }

    @Override
    @PostMapping(value = "/create")
    public ResponseEntity<DataDTO<Long>> create(@RequestBody AdvertisementCreateDTO dto) {
        return service.create(dto);
    }

    @Override
    @PostMapping(value = "/update")
    public ResponseEntity<DataDTO<Long>> update(AdvertisementUpdateDTO dto) {
        return service.update(dto);
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DataDTO<Boolean>> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping(value = "/save-advertisement/{id}")
    public ResponseEntity<DataDTO<Boolean>> save(@PathVariable Long id) {
        return service.save(id);
    }

    @GetMapping("/list-daily")
    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllDaily(AdvertisementCriteria criteria) {
        return service.getAllDaily(criteria);
    }

    @GetMapping(value = "/list-long-term")
    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllLongTerm(AdvertisementCriteria criteria) {
        return service.getAllLongTerm(criteria);
    }

    @GetMapping(value = "/list-my")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAllMyList(AdvertisementCriteria criteria) {
        return service.getAllMyList(criteria);
    }

    @GetMapping(value = "/list-saved")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getMySave(AdvertisementCriteria criteria) {
        return service.getAllMySave(criteria);
    }

    @GetMapping(value = "/list-last")
    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllLast(@RequestBody AdvertisementCriteria criteria) {
        return service.getAllLast(criteria);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAllBySearch(@RequestBody SearchCriteria criteria) {
        return service.getAllBySearch(criteria);
    }

}
