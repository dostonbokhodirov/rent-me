package uz.unicorn.rentme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.unicorn.rentme.entity.AuthUser;
import uz.unicorn.rentme.repository.base.BaseRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>, BaseRepository {

    Optional<AuthUser> findByPhoneNumber(String phoneNumber);

}
