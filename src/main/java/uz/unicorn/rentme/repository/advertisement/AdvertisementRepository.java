package uz.unicorn.rentme.repository.advertisement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.repository.base.BaseRepository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, BaseRepository {
    @Query(value = "select a.* from advertisement a " +
            "inner join auth_user_advertisement aua on a.id = aua.advertisement_id where aua.auth_user_id=:userId and a.deleted='f'"
            , nativeQuery = true)
    Page<Advertisement> findAllByUserIdAndDeletedFalse(Pageable pageable, Long userId);

    Advertisement findByIdAndDeletedFalse(Long id);

    Page<Advertisement> findAllByCreatedBy(Pageable pageable, Long id);
}
