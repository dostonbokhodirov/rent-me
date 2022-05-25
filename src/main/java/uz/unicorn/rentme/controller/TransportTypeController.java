package uz.unicorn.rentme.controller;

import org.springframework.web.bind.annotation.*;
import uz.unicorn.rentme.controller.base.AbstractController;
import uz.unicorn.rentme.criteria.AdvertisementCriteria;
import uz.unicorn.rentme.dto.advertisement.AdvertisementDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeCreateDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.TransportTypeService;

import java.util.List;

@RestController
@RequestMapping("/transport-type")
public class TransportTypeController extends AbstractController<TransportTypeService> {

    public TransportTypeController(TransportTypeService service) {
        super(service);
    }

    @GetMapping("/get-name/{name}")
    public ResponseEntity<DataDTO<List<String>>> getTransportTypeVal(@PathVariable(value = "name") String type) {
        return service.getTransportTypeVal(type);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<DataDTO<Long>> create(@RequestBody TransportTypeCreateDTO dto) {
        return service.create(dto);
    }

    @GetMapping(value = "/list-details")
    public ResponseEntity<DataDTO<List<TransportTypeDTO>>> getAllDetails() {
        return service.getAll(null);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<DataDTO<List<String>>> getAll() {
        return service.getAllName();
    }
}
