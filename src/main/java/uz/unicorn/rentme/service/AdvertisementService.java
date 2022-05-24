package uz.unicorn.rentme.service;

import org.mapstruct.ap.internal.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.unicorn.rentme.config.security.utils.UtilsForSessionUser;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementService extends AbstractService<AdvertisementMapper, AdvertisementRepository>
        implements GenericCrudService<AdvertisementDTO, AdvertisementCreateDTO, AdvertisementUpdateDTO, AdvertisementCriteria> {

    private final UtilsForSessionUser utils;

    public AdvertisementService(AdvertisementMapper mapper, AdvertisementRepository repository, UtilsForSessionUser utils) {
        super(mapper, repository);
        this.utils = utils;
    }

    @Override
    public ResponseEntity<DataDTO<Long>> create(AdvertisementCreateDTO dto) {
        Advertisement advertisement = mapper.fromCreateDTO(dto);
        advertisement.getTransport().getPictures().forEach(item -> item.setTransport(advertisement.getTransport()));
        advertisement.getPrices().forEach(item -> item.setAdvertisement(advertisement));
        Advertisement save = repository.save(advertisement);
        return new ResponseEntity<>(new DataDTO<>(save.getId()));
    }

    @Override
    public ResponseEntity<DataDTO<Long>> update(AdvertisementUpdateDTO dto) {
        Advertisement advertisement = repository.findByIdAndDeletedFalse(dto.getId()).orElseThrow(() -> {
            throw new NotFoundException("Advertisement not found");
        });
        Advertisement advertisement1 = mapper.fromUpdateDTO(dto, advertisement);
        Advertisement save = repository.save(advertisement1);
        return new ResponseEntity<>(new DataDTO<>(save.getId()));
    }

    @Override
    @Transactional
    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        Advertisement advertisement = repository.findByIdAndDeletedFalse(id).orElseThrow(() -> {
            throw new NotFoundException("Advertisement not found");
        });
        repository.delete(advertisement);
        return new ResponseEntity<>(new DataDTO<>(true));
    }

    @Override
    public ResponseEntity<DataDTO<AdvertisementDTO>> get(Long id) {
        Advertisement advertisement = repository.findByIdAndDeletedFalse(id).orElseThrow(() -> {
            throw new NotFoundException("Advertisement not found");
        });
        AdvertisementDTO dto = mapper.toDTO(advertisement);
        return new ResponseEntity<>(new DataDTO<>(dto));
    }

    @Override
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAll(AdvertisementCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        Page<Advertisement> allByCreatedByAndDeletedFalse = repository.findAllByDeletedFalse(pageable);
        List<Advertisement> advertisements = allByCreatedByAndDeletedFalse.stream().toList();
        List<AdvertisementDTO> advertisementDTOS = mapper.toDTO(advertisements);
        return new ResponseEntity<>(new DataDTO<>(advertisementDTOS, (long) advertisementDTOS.size()));
    }

    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAllMyList(AdvertisementCriteria criteria) {
        List<Advertisement> advertisements = repository.findByCreatedByAndDeletedFalse(utils.getSessionId(), criteria.getPage(), criteria.getSize());
        List<AdvertisementDTO> advertisementDTOS = mapper.toDTO(advertisements);
        return new ResponseEntity<>(new DataDTO<>(advertisementDTOS, (long) advertisementDTOS.size()));
    }

    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAllMySave(AdvertisementCriteria criteria) {
        List<Advertisement> collect = repository.findAllByUserIdAndDeletedFalse(utils.getSessionId(), criteria.getPage(), criteria.getSize());
        List<AdvertisementDTO> advertisementDTOS = mapper.toDTO(collect);
        return new ResponseEntity<>(new DataDTO<>(advertisementDTOS, (long) advertisementDTOS.size()));
    }

    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllDaily(AdvertisementCriteria criteria) {
        List<AdvertisementShortDTO> advertisementShortDTOList = new ArrayList<>();
        String json = repository.findAllByMaxDurationLessThanJson(30L, criteria.getPage(), criteria.getSize());
        if (Strings.isNotEmpty(json)) advertisementShortDTOList = getResponse(json);
        return new ResponseEntity<>(new DataDTO<>(advertisementShortDTOList, (long) advertisementShortDTOList.size()));
    }

    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllWeekly(AdvertisementCriteria criteria) {
        List<AdvertisementShortDTO> advertisementShortDTOList = new ArrayList<>();
        String json = repository.findAllByMaxDurationGreaterThanJson(30L, criteria.getPage(), criteria.getSize());
        if (Strings.isNotEmpty(json)) advertisementShortDTOList = getResponse(json);
        return new ResponseEntity<>(new DataDTO<>(advertisementShortDTOList, (long) advertisementShortDTOList.size()));
    }

    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllLast(AdvertisementCriteria criteria) {
        List<AdvertisementShortDTO> advertisementShortDTOList = new ArrayList<>();
        String json = repository.findAllByLast(criteria.getPage(), criteria.getSize());
        if (Strings.isNotEmpty(json)) advertisementShortDTOList = getResponse(json);
        return new ResponseEntity<>(new DataDTO<>(advertisementShortDTOList, (long) advertisementShortDTOList.size()));
    }

    public ResponseEntity<DataDTO<Boolean>> save(Long id) {
        repository.saveMyAdvertisement(id, utils.getSessionId());
        return new ResponseEntity<>(new DataDTO<>(true));
    }
}
