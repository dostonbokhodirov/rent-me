package uz.unicorn.rentme.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.unicorn.rentme.controller.base.AbstractController;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.TransportTypeService;

import java.util.List;

@RestController
@RequestMapping("/enum")
public class EnumController extends AbstractController<TransportTypeService> {

    public EnumController(TransportTypeService service) { super(service); }

    @PostMapping("/{type}")
    public ResponseEntity<DataDTO<List<String>>> getTransportTypeVal(@PathVariable String type){
        return service.getTransportTypeVal(type);
    }
}
