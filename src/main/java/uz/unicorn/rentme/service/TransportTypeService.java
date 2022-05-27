package uz.unicorn.rentme.service;

import org.springframework.stereotype.Service;
import uz.unicorn.rentme.criteria.base.AbstractCriteria;
import uz.unicorn.rentme.dto.transportType.TransportTypeCreateDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeUpdateDTO;
import uz.unicorn.rentme.entity.TransportModel;
import uz.unicorn.rentme.mapper.TransportTypeMapper;
import uz.unicorn.rentme.repository.TransportTypeRepository;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.AbstractService;
import uz.unicorn.rentme.service.base.GenericCrudService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransportTypeService extends AbstractService<TransportTypeMapper, TransportTypeRepository>
        implements GenericCrudService<TransportTypeDTO, TransportTypeCreateDTO, TransportTypeUpdateDTO, AbstractCriteria> {


    public TransportTypeService(TransportTypeMapper mapper, TransportTypeRepository repository) {
        super(mapper, repository);
    }

    public ResponseEntity<DataDTO<List<String>>> getTransportTypeVal(String str) {
        Optional<List<TransportModel>> optional = repository.findByNameStartingWith(str);
        if (optional.isPresent()) {
            List<String> result = optional.get().stream().map(TransportModel::getName).collect(Collectors.toList());
            return new ResponseEntity<>(new DataDTO<>(result, (long) result.size()));
        }
        return new ResponseEntity<>(new DataDTO<>(null));
    }

    @Override
    public ResponseEntity<DataDTO<Long>> create(TransportTypeCreateDTO dto) {
        TransportModel transportModel = mapper.fromCreateDTO(dto);
        TransportModel save = repository.save(transportModel);
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
        List<TransportModel> transportModelList = repository.findAll();
        List<TransportTypeDTO> transportTypeDTOList = mapper.toDTO(transportModelList);
        return new ResponseEntity<>(new DataDTO<>(transportTypeDTOList, (long) transportTypeDTOList.size()));
    }

    public ResponseEntity<DataDTO<List<String>>> getAllName() {
        List<TransportModel> all = repository.findAll();
        List<String> stringList = all.stream().map(TransportModel::getName).collect(Collectors.toList());
        return new ResponseEntity<>(new DataDTO<>(stringList, (long) stringList.size()));
    }
}
