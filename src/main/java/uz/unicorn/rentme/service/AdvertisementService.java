package uz.unicorn.rentme.service;

import org.springframework.stereotype.Service;
import uz.unicorn.rentme.criteria.AdvertisementCriteria;
import uz.unicorn.rentme.dto.advertisement.AdvertisementCreateDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementUpdateDTO;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.entity.Transport;
import uz.unicorn.rentme.exceptions.NotFoundException;
import uz.unicorn.rentme.mapper.AdvertisementMapper;
import uz.unicorn.rentme.repository.AdvertisementRepository;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.AbstractService;
import uz.unicorn.rentme.service.base.GenericCrudService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdvertisementService extends AbstractService<AdvertisementMapper, AdvertisementRepository>
implements GenericCrudService<AdvertisementDTO, AdvertisementCreateDTO, AdvertisementUpdateDTO, AdvertisementCriteria> {

    public AdvertisementService(AdvertisementMapper mapper, AdvertisementRepository repository) {
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
        Optional<Advertisement> advertisementOptional = Optional.ofNullable(repository.findByIdAndDeletedFalse(id));
        if (advertisementOptional.isEmpty())
            throw new NotFoundException("Advertisement not found");
        Advertisement advertisement = advertisementOptional.get();
        return new ResponseEntity<>(new DataDTO<>(mapper.toDTO(advertisement)));
    }

    @Override
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAll(AdvertisementCriteria criteria) {
        return null;
    }

}
