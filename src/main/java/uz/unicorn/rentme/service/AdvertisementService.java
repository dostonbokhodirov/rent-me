package uz.unicorn.rentme.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.unicorn.rentme.criteria.AdvertisementCriteria;
import uz.unicorn.rentme.dto.advertisement.AdvertisementCreateDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementShortDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementUpdateDTO;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.exceptions.NotFoundException;
import uz.unicorn.rentme.mapper.AdvertisementMapper;
import uz.unicorn.rentme.repository.AdvertisementRepository;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.AbstractService;
import uz.unicorn.rentme.service.base.GenericCrudService;

import java.util.List;

@Service
public class AdvertisementService extends AbstractService<AdvertisementMapper, AdvertisementRepository>
        implements GenericCrudService<AdvertisementDTO, AdvertisementCreateDTO, AdvertisementUpdateDTO, AdvertisementCriteria> {

    public AdvertisementService(/*@Qualifier("AdvertisementMapperImpl") */AdvertisementMapper mapper, AdvertisementRepository repository) {
        super(mapper, repository);
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

    @Override
    public ResponseEntity<DataDTO<AdvertisementDTO>> get(Long id) {
        Advertisement advertisement = repository
                .findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Advertisement not found"));
        AdvertisementDTO dto = mapper.toDTO(advertisement);
        return new ResponseEntity<>(new DataDTO<>(dto));
    }

    @Override
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAll(AdvertisementCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Advertisement> advertisementList = repository
                .findAllByUserId(pageable, criteria.getUserId())
                .orElseThrow(() -> new NotFoundException("Advertisements not found"));
        List<AdvertisementDTO> advertisementDTOList = mapper.toDTO(advertisementList);
        return new ResponseEntity<>(new DataDTO<>(advertisementDTOList, (long) advertisementDTOList.size()));
    }

    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllWeekly(AdvertisementCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<AdvertisementShortDTO> advertisementList = repository
                .findAllByMaxDurationGreaterThan(30L, pageable)
                .orElseThrow(() -> new NotFoundException("Advertisement not found"));
        return new ResponseEntity<>(new DataDTO<>(advertisementList, (long) advertisementList.size()));
    }
}
