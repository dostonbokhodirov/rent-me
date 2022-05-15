package uz.unicorn.rentme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.unicorn.rentme.entity.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
