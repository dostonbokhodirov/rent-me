package uz.unicorn.rentme.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import uz.unicorn.rentme.exceptions.BadRequestException;
import uz.unicorn.rentme.exceptions.NotFoundException;
import uz.unicorn.rentme.mapper.AdvertisementMapper;
import uz.unicorn.rentme.repository.AdvertisementRepository;
import uz.unicorn.rentme.repository.AuthUserRepository;
import uz.unicorn.rentme.repository.TransportModelRepository;
import uz.unicorn.rentme.response.DataDTO;
import uz.unicorn.rentme.response.ResponseEntity;
import uz.unicorn.rentme.service.base.AbstractService;
import uz.unicorn.rentme.service.base.GenericCrudService;
import uz.unicorn.rentme.utils.DateUtils;

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
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Advertisement> allByMaxDurationLessThanJson = repository.findAllByMaxDurationLessThan(30, pageable);
        return getDataDTOResponseEntity(allByMaxDurationLessThanJson);
    }

    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllLongTerm(AdvertisementCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Advertisement> allByMaxDurationGreaterThanJson = repository.findAllByMaxDurationGreaterThan(30, pageable);

        return getDataDTOResponseEntity(allByMaxDurationGreaterThanJson);
    }

    public ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getAllLast(AdvertisementCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Advertisement> allByLast = repository.findAllByLast(pageable);
        return getDataDTOResponseEntity(allByLast);
    }

    private ResponseEntity<DataDTO<List<AdvertisementShortDTO>>> getDataDTOResponseEntity(List<Advertisement> allByLast) {
        allByLast.
                forEach(item -> item.getTransport().getPictures().
                        stream().
                        filter(p -> p.getMain().equals(true)).
                        forEach(p -> item.getTransport().setPictures(List.of(p))));
        List<AdvertisementShortDTO> advertisementShortDTOS = mapper.toShortDTO(allByLast);
        return new ResponseEntity<>(new DataDTO<>(advertisementShortDTOS, (long) advertisementShortDTOS.size()));
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
        if (Objects.nonNull(criteria.getLocation())) {
            whereCause.add("(3959 * acos(cos(radians(:latitude)) * cos(radians(a.location.latitude)) " +
                    "* cos(radians(a.location.longitude) - radians(:longitude)) + sin(radians(:latitude)) " +
                    "* sin(radians(a.location.latitude)))) < :distance");
            if (Objects.isNull(criteria.getLocation().getLatitude())) {
                throw new BadRequestException("Latitude cannot be null");
            }
            if (Objects.isNull(criteria.getLocation().getLongitude())) {
                throw new BadRequestException("Longitude cannot be null");
            }
            params.put("distance", criteria.getLocation().getDistance() / 1.6);
            params.put("latitude", criteria.getLocation().getLatitude());
            params.put("longitude", criteria.getLocation().getLongitude());
        }

        if (Objects.nonNull(criteria.getPrice()) && Objects.nonNull(criteria.getPrice().getType())) {
            if (Objects.isNull(criteria.getPrice().getType())) {
                throw new BadRequestException("Price type cannot be null");
            }
            whereCause.add("p.type = :type");
            whereCause.add("p.quantity between :minPrice and :maxPrice");
            params.put("type", criteria.getPrice().getType());
            params.put("minPrice", criteria.getPrice().getMinPrice());
            params.put("maxPrice", criteria.getPrice().getMaxPrice());
        }

        if (Objects.nonNull(criteria.getDate())) {
            if (criteria.getDate().getStartDate().after(criteria.getDate().getEndDate())) {
                throw new BadRequestException("Start Date must be before than ending date");
            }
            whereCause.add("a.startDate < :startDate");
            whereCause.add("a.maxDuration > :maxDuration ");
            params.put("startDate",
                    DateUtils.toLocalDate(criteria.getDate().getStartDate()));
            params.put("maxDuration",
                    DateUtils.getDifferenceDays(criteria.getDate().getEndDate(), criteria.getDate().getStartDate()));
        }
        TypedQuery<Advertisement> query = getQuery(whereCause, params);
        List<Advertisement> advertisementList;
        advertisementList = getResults(criteria, query);
        List<AdvertisementDTO> advertisementDTOList = mapper.toDTO(advertisementList);
        return new ResponseEntity<>(new DataDTO<>(advertisementDTOList, (long) advertisementDTOList.size()));
    }

    private List<Advertisement> getResults(SearchCriteria criteria, TypedQuery<Advertisement> query) {
        return Objects.isNull(criteria.getPage()) || Objects.isNull(criteria.getSize())
                ? query.getResultList()
                : query
                .setFirstResult(criteria.getPage() * criteria.getSize())
                .setMaxResults(criteria.getSize())
                .getResultList();
    }

    private TypedQuery<Advertisement> getQuery(List<String> whereCause, Map<String, Object> params) {
        String q = "select a from Advertisement a inner join a.prices p " +
                "where a.deleted is false and " +
                StringUtils.join(whereCause, " and ");
        TypedQuery<Advertisement> query = entityManager.createQuery(q, Advertisement.class);
        params.keySet().forEach(t -> query.setParameter(t, params.get(t)));
        return query;
    }

}
