package uz.unicorn.rentme.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.unicorn.rentme.criteria.base.AbstractCriteria;
import uz.unicorn.rentme.dto.transportModel.TransportModelCreateDTO;
import uz.unicorn.rentme.dto.transportModel.TransportModelDTO;
import uz.unicorn.rentme.dto.transportModel.TransportModelUpdateDTO;
import uz.unicorn.rentme.entity.TransportModel;
import uz.unicorn.rentme.exceptions.NotFoundException;
import uz.unicorn.rentme.mapper.TransportModelMapper;
import uz.unicorn.rentme.repository.TransportModelRepository;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.AbstractService;
import uz.unicorn.rentme.service.base.GenericCrudService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransportModelService extends AbstractService<TransportModelMapper, TransportModelRepository>
        implements GenericCrudService<TransportModelDTO, TransportModelCreateDTO, TransportModelUpdateDTO, AbstractCriteria> {


    public TransportModelService(@Qualifier("transportModelMapperImpl") TransportModelMapper mapper, TransportModelRepository repository) {
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
    public ResponseEntity<DataDTO<Long>> create(TransportModelCreateDTO dto) {
        TransportModel transportModel = mapper.fromCreateDTO(dto);
        TransportModel save = repository.save(transportModel);
        return new ResponseEntity<>(new DataDTO<>(save.getId()));
    }

    @Override
    public ResponseEntity<DataDTO<Long>> update(TransportModelUpdateDTO dto) {
        TransportModel type = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new NotFoundException("TransportModel not found");
        });
        TransportModel type1 = mapper.fromUpdateDTO(dto, type);
        repository.save(type1);
        return new ResponseEntity<>(new DataDTO<>(dto.getId()));
    }

    @Override
    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        TransportModel type = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("TransportModel not found");
        });
        type.setDeleted(true);
        repository.save(type);
        return new ResponseEntity<>(new DataDTO<>(true));
    }

    @Override
    public ResponseEntity<DataDTO<TransportModelDTO>> get(Long id) {
        TransportModel type = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("TransportModel not found");
        });
        TransportModelDTO dto = mapper.toDTO(type);
        return new ResponseEntity<>(new DataDTO<>(dto));
    }

    @Override
    public ResponseEntity<DataDTO<List<TransportModelDTO>>> getAll(AbstractCriteria criteria) {
        List<TransportModel> transportTypeList = repository.findAll();
        List<TransportModelDTO> transportTypeDTOList = mapper.toDTO(transportTypeList);
        return new ResponseEntity<>(new DataDTO<>(transportTypeDTOList, (long) transportTypeDTOList.size()));
    }

    public ResponseEntity<DataDTO<List<String>>> getAllName() {
        List<TransportModel> all = repository.findAll();
        List<String> stringList = all.stream().map(TransportModel::getName).collect(Collectors.toList());
        return new ResponseEntity<>(new DataDTO<>(stringList, (long) stringList.size()));
    }
}
