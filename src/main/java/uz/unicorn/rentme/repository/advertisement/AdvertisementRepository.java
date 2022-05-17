package uz.unicorn.rentme.repository.advertisement;

import org.hibernate.internal.SessionImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.repository.base.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, BaseRepository {
    @Query(value = "select a.* from advertisement a " +
            "inner join auth_user_advertisement aua on a.id = aua.advertisement_id where aua.auth_user_id=:userId"
            , nativeQuery = true)
    Page<Advertisement> findByUserId(Pageable pageable, Long userId);
}
