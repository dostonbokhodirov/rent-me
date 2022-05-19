package uz.unicorn.rentme.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.unicorn.rentme.dto.advertisement.AdvertisementShortDTO;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, BaseRepository {
    @Query(value = "select a.* from public.advertisement a " +
            "inner join public.auth_user_advertisement aua on a.id = aua.advertisement_id where aua.auth_user_id=:userId"
            , nativeQuery = true)
    Optional<List<Advertisement>> findAllByUserId(Pageable pageable, Long userId);

    Optional<Advertisement> findByIdAndDeletedFalse(Long id);

    Optional<List<Advertisement>> findAllByMaxDurationLessThan(Long maxDuration);

    @Query(
            value = "select a.id, a.description, a.price, p.path, a.category, t.* " +
                    "from public.advertisement a " +
                    "inner join transport t on t.id = a.transport_id " +
                    "inner join picture p on t.id = p.transport_id " +
                    "where a.max_duration > :maxDuration and p.main is true " +
                    "order by a.id",
            nativeQuery = true
    )
    Optional<List<AdvertisementShortDTO>> findAllByMaxDurationGreaterThan(
            @Param(value = "maxDuration") Long maxDuration,
            Pageable pageable);
}
