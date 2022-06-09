package uz.unicorn.rentme.controller;

import org.springframework.web.bind.annotation.*;
import uz.unicorn.rentme.controller.base.AbstractController;
import uz.unicorn.rentme.controller.base.GenericCrudController;
import uz.unicorn.rentme.criteria.base.AbstractCriteria;
import uz.unicorn.rentme.dto.brand.BrandCreateDTO;
import uz.unicorn.rentme.dto.brand.BrandDTO;
import uz.unicorn.rentme.dto.brand.BrandUpdateDTO;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController
        extends AbstractController<BrandService>
        implements GenericCrudController<
        BrandDTO,
        BrandCreateDTO,
        BrandUpdateDTO,
        AbstractCriteria> {

    public BrandController(BrandService service) {
        super(service);
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseEntity<DataDTO<BrandDTO>> get(@PathVariable Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<DataDTO<List<BrandDTO>>> getAll(AbstractCriteria criteria) {
        return service.getAll(criteria);
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<DataDTO<Long>> create(@RequestBody BrandCreateDTO dto) {
        return service.create(dto);
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<DataDTO<Long>> update(@RequestBody BrandUpdateDTO dto) {
        return service.update(dto);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataDTO<Boolean>> delete(@PathVariable Long id) {
        return service.delete(id);
    }

}
