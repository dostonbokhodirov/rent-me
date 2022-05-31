package uz.unicorn.rentme.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.unicorn.rentme.config.security.utils.UtilsForSessionUser;
import uz.unicorn.rentme.criteria.AdvertisementCriteria;
import uz.unicorn.rentme.criteria.SearchCriteria;
import uz.unicorn.rentme.dto.advertisement.AdvertisementCreateDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementShortDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementUpdateDTO;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.entity.Transport;
import uz.unicorn.rentme.entity.TransportModel;
import uz.unicorn.rentme.exceptions.NotFoundException;
import uz.unicorn.rentme.mapper.AdvertisementMapper;
import uz.unicorn.rentme.repository.AdvertisementRepository;
import uz.unicorn.rentme.repository.AuthUserRepository;
import uz.unicorn.rentme.repository.TransportModelRepository;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.AbstractService;
import uz.unicorn.rentme.service.base.GenericCrudService;

import javax.persistence.TypedQuery;
import java.util.*;

@Service
public class AdvertisementService extends AbstractService<AdvertisementMapper, AdvertisementRepository>
        implements GenericCrudService<AdvertisementDTO, AdvertisementCreateDTO, AdvertisementUpdateDTO, AdvertisementCriteria> {

    private final UtilsForSessionUser utils;
    private final TransportModelRepository transportModelRepository;
    private final AuthUserRepository authUserRepository;

    public AdvertisementService(
            @Qualifier("advertisementMapperImpl") AdvertisementMapper mapper,
            AdvertisementRepository repository,
            UtilsForSessionUser utils,
            TransportModelRepository transportModelRepository,
            AuthUserRepository authUserRepository) {

        super(mapper, repository);
        this.utils = utils;
        this.transportModelRepository = transportModelRepository;
        this.authUserRepository = authUserRepository;

    }

    @Override
    public ResponseEntity<DataDTO<Long>> create(AdvertisementCreateDTO dto) {
        Advertisement advertisement = mapper.fromCreateDTO(dto);
        TransportModel transportModel = transportModelRepository
                .findByName(dto.getTransport().getModel())
                .orElseThrow(() -> new NotFoundException("Transport type not found"));
        Transport transport = advertisement.getTransport();
        transport.setModel(transportModel);  //set Transport model
        advertisement.setTransport(transport); // set Transport
        advertisement.getTransport().getPictures().forEach(item -> item.setTransport(advertisement.getTransport())); //set picture path
        advertisement.getPrices().forEach(item -> item.setAdvertisement(advertisement)); //set Advertisement to Price table
        Advertisement save = repository.save(advertisement); //save data
        return new ResponseEntity<>(new DataDTO<>(save.getId()));
    }

    @Override
    public ResponseEntity<DataDTO<Long>> update(AdvertisementUpdateDTO dto) {
        Advertisement advertisement = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new NotFoundException("Advertisement not found");
        });
        Advertisement advertisement1 = mapper.fromUpdateDTO(dto, advertisement);
        repository.save(advertisement1);
        return new ResponseEntity<>(new DataDTO<>(dto.getId()));
    }

    @Override
    @Transactional
    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        Advertisement advertisement = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Advertisement not found");
        });
        advertisement.setDeleted(true);
        repository.save(advertisement);
        return new ResponseEntity<>(new DataDTO<>(true));
    }

    @Override
    public ResponseEntity<DataDTO<AdvertisementDTO>> get(Long id) {
        Advertisement advertisement = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Advertisement not found");
        });
        AdvertisementDTO dto = mapper.toDTO(advertisement);
        return new ResponseEntity<>(new DataDTO<>(dto));
    }

    @Override
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAll(AdvertisementCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Advertisement> advertisements = repository.findAll(pageable).getContent();
        List<AdvertisementDTO> advertisementDTOS = mapper.toDTO(advertisements);
        return new ResponseEntity<>(new DataDTO<>(advertisementDTOS, (long) advertisementDTOS.size()));
    }

    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAllMyList(AdvertisementCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Advertisement> advertisements = repository.findAllByCreatedBy(utils.getSessionId(), pageable);
        List<AdvertisementDTO> advertisementDTOS = mapper.toDTO(advertisements);
        return new ResponseEntity<>(new DataDTO<>(advertisementDTOS, (long) advertisementDTOS.size()));
    }

    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAllMySave(AdvertisementCriteria criteria) {
        Long sessionId = utils.getSessionId();
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Advertisement> top = authUserRepository.findAuthUserAdvertisements(sessionId, pageable);
        List<AdvertisementDTO> collect = mapper.toDTO(top);
        return new ResponseEntity<>(new DataDTO<>(collect, (long) collect.size()));
    }

    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllDaily(AdvertisementCriteria criteria) {
        List<AdvertisementShortDTO> advertisementShortDTOList = new ArrayList<>();
        String json = repository.findAllByMaxDurationLessThanJson(30L, criteria.getPage(), criteria.getSize());
        if (StringUtils.isNotEmpty(json)) advertisementShortDTOList = getResponse(json);
        return new ResponseEntity<>(new DataDTO<>(advertisementShortDTOList, (long) advertisementShortDTOList.size()));
    }

    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllLongTerm(AdvertisementCriteria criteria) {
        List<AdvertisementShortDTO> advertisementShortDTOList = new ArrayList<>();
        String json = repository.findAllByMaxDurationGreaterThanJson(30L, criteria.getPage(), criteria.getSize());
        if (StringUtils.isNotEmpty(json)) advertisementShortDTOList = getResponse(json);
        return new ResponseEntity<>(new DataDTO<>(advertisementShortDTOList, (long) advertisementShortDTOList.size()));
    }

    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllLast(AdvertisementCriteria criteria) {
        List<AdvertisementShortDTO> advertisementShortDTOList = new ArrayList<>();
        String json = repository.findAllByLast(criteria.getPage(), criteria.getSize());
        if (StringUtils.isNotEmpty(json)) advertisementShortDTOList = getResponse(json);
        return new ResponseEntity<>(new DataDTO<>(advertisementShortDTOList, (long) advertisementShortDTOList.size()));
    }

    @Transactional
    public ResponseEntity<DataDTO<Boolean>> save(Long id) {
        repository.saveMyAdvertisement(id, utils.getSessionId());
        return new ResponseEntity<>(new DataDTO<>(true));
    }

    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAllBySearch(SearchCriteria criteria) {

        List<String> whereCause = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        if (Objects.nonNull(criteria.getCategory())) {
            whereCause.add("category = :category");
            params.put("category", criteria.getCategory());
        }

        if (Objects.nonNull(criteria.getModel())) {
            TransportModel transportModel = transportModelRepository
                    .findByName(criteria.getModel())
                    .orElseThrow(() -> new NotFoundException("Transport model name is invalid"));
            whereCause.add("a.transport.model = :model");
            params.put("model", transportModel);
        }

        if (Objects.nonNull(criteria.getYear())) {
            whereCause.add("a.transport.year = :year");
            params.put("year", criteria.getYear());
        }

        if (Objects.nonNull(criteria.getColors())) {
            whereCause.add("a.transport.color in :colors");
            params.put("colors", criteria.getColors());
        }

        // TODO: 5/29/2022 location | prices | dates

        String q = "from Advertisement a " +
                "where a.deleted is false and " +
                StringUtils.join(whereCause, " and ");

        TypedQuery<Advertisement> query = entityManager.createQuery(q, Advertisement.class);
        params.keySet().forEach(t -> query.setParameter(t, params.get(t)));

        List<Advertisement> resultList = query.getResultList();
        List<AdvertisementDTO> advertisementDTOList = mapper.toDTO(resultList);

        return new ResponseEntity<>(new DataDTO<>(advertisementDTOList, (long) advertisementDTOList.size()));

    }
}
