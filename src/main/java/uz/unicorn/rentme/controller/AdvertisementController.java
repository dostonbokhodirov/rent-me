package uz.unicorn.rentme.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<DataDTO<AdvertisementDTO>> get(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAll(AdvertisementCriteria criteria) {
        return null;
    }

    @Override
    public ResponseEntity<DataDTO<Long>> create(AdvertisementCreateDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDTO<Long>> update(AdvertisementUpdateDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        return null;
    }

}