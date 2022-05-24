package uz.unicorn.rentme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, BaseRepository {
    Optional<Advertisement> findByIdAndDeletedFalse(Long id);

    Optional<List<Advertisement>> findAllByMaxDurationLessThan(Long maxDuration);

    //    @Query(value = "select new  uz.unicorn.rentme.dto.advertisement.AdvertisementShortDTO(a.id, a.description, p.path, a.price, a.category, new uz.unicorn.rentme.dto.transport.TransportDTO(a.transport.type, a.transport.model, a.transport.year, a.transport.transmission, a.transport.fuelType, a.transport.color, a.transport.pictures, a.transport.wellEquipped)) from Advertisement a " +
//            "inner join a.transport inner join Picture p " +
//            "inner join a.transport.pictures " +
//            "where a.maxDuration > :maxDuration and p.main is true " +
//            "order by a.id")
//    Optional<List<AdvertisementShortDTO>> findAllByMaxDurationGreaterThan(
//            @Param(value = "maxDuration") Long maxDuration,
//            Pageable pageable);
//
    @Query(
            value = "select cast((select array_to_json(array_agg(row_to_json(my_table))) " +
                    "from (select a.id, a.description, a.price, p.path, a.category, t.* " +
                    "from public.advertisement a " +
                    "inner join transport t on t.id = a.transport_id " +
                    "inner join picture p on t.id = p.transport_id " +
                    "where a.max_duration > :maxDuration and p.main is true " +
                    "order by a.id limit :size offset (:page - 1) * :size) as my_table) as text)",
            nativeQuery = true
    )
    String findAllByMaxDurationGreaterThanJson(@Param(value = "maxDuration") Long maxDuration,
                                               @Param(value = "page") Integer page,
                                               @Param(value = "size") Integer size);

    @Query(value = "select a.* from advertisement a " +
            "inner join auth_user_advertisement aua on a.id = aua.advertisement_id " +
            "where aua.auth_user_id=:userId and a.deleted='f' ",
            nativeQuery = true
    )
    Page<Advertisement> findAllByUserIdAndDeletedFalse(Pageable pageable, Long userId);

    Page<Advertisement> findByCreatedByAndDeletedFalse(Long id, Pageable pageable);

    @Query(
            value = "select cast((select array_to_json(array_agg(row_to_json(my_table))) " +
                    "from (select a.id, a.description, a.price, p.path, a.category, t.* " +
                    "from public.advertisement a " +
                    "inner join transport t on t.id = a.transport_id " +
                    "inner join picture p on t.id = p.transport_id " +
                    "where a.max_duration <= :maxDuration and p.main is true " +
                    "order by a.id limit :size offset (:page - 1) * :size) as my_table) as text)",
            nativeQuery = true
    )
    String findAllByMaxDurationLessThanJson(@Param(value = "maxDuration") Long maxDuration,
                                            @Param(value = "page") Integer page,
                                            @Param(value = "size") Integer size);

    @Query(
            value = "select cast((select array_to_json(array_agg(row_to_json(my_table))) " +
                    "from (select a.id, a.description, a.price, p.path, a.category, t.* " +
                    "from public.advertisement a " +
                    "inner join transport t on t.id = a.transport_id " +
                    "inner join picture p on t.id = p.transport_id " +
                    "where  not p.deleted and not t.deleted and not a.deleted and p.main is true " +
                    "order by a.created_at desc limit :size offset (:page - 1) * :size) as my_table) as text)",
            nativeQuery = true
    )
    String findAllByLast(@Param(value = "page") Integer page, @Param(value = "size") Integer size);

    Page<Advertisement> findAllByDeletedFalse(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "insert into public.auth_user_advertisement" +
            " (auth_user_id,advertisement_id) value (:userId,:id)", nativeQuery = true)
    void saveMyAdvertisement(Long id, Long userId);
}
