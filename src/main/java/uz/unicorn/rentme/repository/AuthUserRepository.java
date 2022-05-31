package uz.unicorn.rentme.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.entity.AuthUser;
import uz.unicorn.rentme.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>, BaseRepository {
    Optional<AuthUser> findByPhoneNumber(String phoneNumber);


    @Query(value = "select a.savedAdvertisements from AuthUser a where a.id=:id ")
    List<Advertisement> findAuthUserAdvertisements(Long id, Pageable pageable);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query(value = "select a.id from AuthUser a where a.phoneNumber=:phone ")
    Optional<Long> getUserId(String phone);
}
