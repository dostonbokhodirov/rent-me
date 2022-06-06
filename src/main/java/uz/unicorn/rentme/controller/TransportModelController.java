package uz.unicorn.rentme.controller;

import org.springframework.web.bind.annotation.*;
import uz.unicorn.rentme.controller.base.AbstractController;
import uz.unicorn.rentme.dto.transportModel.TransportModelCreateDTO;
import uz.unicorn.rentme.dto.transportModel.TransportModelDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.TransportModelService;

import java.util.List;

@RestController
@RequestMapping("/transport-model")
public class TransportModelController extends AbstractController<TransportModelService> {

    public TransportModelController(TransportModelService service) {
        super(service);
    }

    @GetMapping("/get-name/{name}")
    public ResponseEntity<DataDTO<List<String>>> getTransportTypeVal(@PathVariable(value = "name") String type) {
        return service.getTransportTypeVal(type);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<DataDTO<Long>> create(@RequestBody TransportModelCreateDTO dto) {
        return service.create(dto);
    }

    @GetMapping(value = "/list-details")
    public ResponseEntity<DataDTO<List<TransportModelDTO>>> getAllDetails() {
        return service.getAll(null);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<DataDTO<List<String>>> getAll() {
        return service.getAllName();
    }

}
