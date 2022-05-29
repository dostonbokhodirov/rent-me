package uz.unicorn.rentme.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.repository.base.BaseRepository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, BaseRepository {

    List<Advertisement> findAllByCreatedBy(Long userId, Pageable pageable);

    @Query(
            value = "select cast((select array_to_json(array_agg(row_to_json(my_table))) " +
                    "from (select a.id, a.description, a.price, p.path, a.category, t.* " +
                    "from public.advertisement a " +
                    "inner join transport t on t.id = a.transport_id " +
                    "inner join picture p on t.id = p.transport_id " +
                    "where a.max_duration > :maxDuration and p.main is true " +
                    "order by a.id limit :size offset :page * :size) as my_table) as text)",
            nativeQuery = true
    )
    String findAllByMaxDurationGreaterThanJson(@Param(value = "maxDuration") Long maxDuration,
                                               @Param(value = "page") Integer page,
                                               @Param(value = "size") Integer size);


    @Query(
            value = "select cast((select array_to_json(array_agg(row_to_json(my_table))) " +
                    "from (select a.id, a.description, a.price, p.path, a.category, t.* " +
                    "from public.advertisement a " +
                    "inner join transport t on t.id = a.transport_id " +
                    "inner join picture p on t.id = p.transport_id " +
                    "where a.max_duration <= :maxDuration and p.main is true " +
                    "order by a.id limit :size offset :page* :size) as my_table) as text)",
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
                    "order by a.created_at desc limit :size offset :page * :size) as my_table) as text)",
            nativeQuery = true
    )
    String findAllByLast(@Param(value = "page") Integer page, @Param(value = "size") Integer size);

    @Modifying
    @Query(value = "insert into public.auth_user_advertisement" +
            " (auth_user_id,advertisement_id) values (:userId,:id)", nativeQuery = true)
    void saveMyAdvertisement(Long id, Long userId);

}
