package uz.unicorn.rentme.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.unicorn.rentme.criteria.base.AbstractCriteria;
import uz.unicorn.rentme.dto.brand.BrandCreateDTO;
import uz.unicorn.rentme.dto.brand.BrandDTO;
import uz.unicorn.rentme.dto.brand.BrandShortDTO;
import uz.unicorn.rentme.dto.brand.BrandUpdateDTO;
import uz.unicorn.rentme.entity.Brand;
import uz.unicorn.rentme.exceptions.NotFoundException;
import uz.unicorn.rentme.mapper.BrandMapper;
import uz.unicorn.rentme.repository.BrandRepository;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.AbstractService;
import uz.unicorn.rentme.service.base.GenericCrudService;

import java.util.List;

@Service
public class BrandService extends AbstractService<BrandMapper, BrandRepository> implements GenericCrudService<
        BrandDTO,
        BrandCreateDTO,
        BrandUpdateDTO,
        AbstractCriteria> {

    public BrandService(BrandMapper mapper, BrandRepository repository) {
        super(mapper, repository);
    }

    @Override
    public ResponseEntity<DataDTO<Long>> create(BrandCreateDTO dto) {
        Brand brand = mapper.fromCreateDTO(dto);
        brand.getModels().forEach(item -> item.setBrand(brand));
        Brand save = repository.save(brand);
        return new ResponseEntity<>(new DataDTO<>(save.getId()));
    }

    @Override
    public ResponseEntity<DataDTO<Long>> update(BrandUpdateDTO dto) {
        Brand brand = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new NotFoundException("Brand not found");
        });
        Brand newBrand = mapper.fromUpdateDTO(dto, brand);
        repository.save(newBrand);
        return new ResponseEntity<>(new DataDTO<>(newBrand.getId()));
    }

    @Override
    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(new DataDTO<>(true));
    }

    @Override
    public ResponseEntity<DataDTO<BrandDTO>> get(Long id) {
        Brand brand = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Brand not found");
        });
        BrandDTO brandDTO = mapper.toDTO(brand);
        return new ResponseEntity<>(new DataDTO<>(brandDTO));
    }

    @Override
    public ResponseEntity<DataDTO<List<BrandDTO>>> getAll(AbstractCriteria criteria) {
        PageRequest pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Brand> all = repository.findAll(pageable).getContent();
        List<BrandDTO> brandDTOS = mapper.toDTO(all);
        return new ResponseEntity<>(new DataDTO<>(brandDTOS, (long) brandDTOS.size()));
    }

    public ResponseEntity<DataDTO<List<BrandDTO>>> getAll() {
        List<Brand> all = repository.findAll();
        List<BrandDTO> brandDTOS = mapper.toDTO(all);
        return new ResponseEntity<>(new DataDTO<>(brandDTOS, (long) brandDTOS.size()));
    }

    public ResponseEntity<DataDTO<List<BrandShortDTO>>> getAllToMain() {
        List<BrandShortDTO> all = repository.findAllToMain();
        return new ResponseEntity<>(new DataDTO<>(all, (long) all.size()));
    }
}
