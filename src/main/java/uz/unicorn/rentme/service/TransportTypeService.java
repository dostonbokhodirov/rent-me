package uz.unicorn.rentme.service;

import org.springframework.stereotype.Service;
import uz.unicorn.rentme.criteria.base.AbstractCriteria;
import uz.unicorn.rentme.dto.transportType.TransportTypeCreateDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeUpdateDTO;
import uz.unicorn.rentme.entity.TransportType;
import uz.unicorn.rentme.mapper.TransportTypeMapper;
import uz.unicorn.rentme.repository.TransportTypeRepository;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.AbstractService;
import uz.unicorn.rentme.service.base.GenericCrudService;

import java.util.List;
import java.util.Optional;

@Service
public class TransportTypeService extends AbstractService<TransportTypeMapper, TransportTypeRepository>
        implements GenericCrudService<TransportTypeDTO, TransportTypeCreateDTO, TransportTypeUpdateDTO, AbstractCriteria> {


    public TransportTypeService(TransportTypeMapper mapper, TransportTypeRepository repository) {
        super(mapper, repository);
    }

    public ResponseEntity<DataDTO<List<String>>> getTransportTypeVal(String str) {
        Optional<List<String>> byNameStartingWith = repository.findByNameStartingWith(str);
        if (byNameStartingWith.isPresent()) {
            return new ResponseEntity<>(new DataDTO<>(byNameStartingWith.get()));
        }
        return new ResponseEntity<>(new DataDTO<>(null));
    }

    @Override
    public ResponseEntity<DataDTO<Long>> create(TransportTypeCreateDTO dto) {
        TransportType transportType = mapper.fromCreateDTO(dto);
        TransportType save = repository.save(transportType);
        return new ResponseEntity<>(new DataDTO<>(save.getId()));
    }

    @Override
    public ResponseEntity<DataDTO<Long>> update(TransportTypeUpdateDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<DataDTO<TransportTypeDTO>> get(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<DataDTO<List<TransportTypeDTO>>> getAll(AbstractCriteria criteria) {
        return null;
    }
}
