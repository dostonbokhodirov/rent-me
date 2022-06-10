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

    @Query(value = "select a.* from public.advertisement a where a.max_duration>=:maxDuration where a.deleted is false order by random()", nativeQuery = true)
    List<Advertisement> findAllByMaxDurationGreaterThan(@Param(value = "maxDuration") int maxDuration, Pageable pageable);

    @Query(value = "select a.* from public.advertisement a where a.max_duration<=:maxDuration where a.deleted is false order by random()", nativeQuery = true)
    List<Advertisement> findAllByMaxDurationLessThan(@Param(value = "maxDuration") int maxDuration, Pageable pageable);

    @Query(value = "from Advertisement a order by a.createdAt desc")
    List<Advertisement> findAllByLast(Pageable pageable);

    @Modifying
    @Query(value = "insert into public.auth_user_advertisement " +
            "(auth_user_id,advertisement_id) values (:userId,:id)", nativeQuery = true)
    void saveMyAdvertisement(Long id, Long userId);

}
