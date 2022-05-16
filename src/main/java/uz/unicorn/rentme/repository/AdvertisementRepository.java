package uz.unicorn.rentme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.repository.base.BaseRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, BaseRepository {
}
