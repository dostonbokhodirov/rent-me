package uz.unicorn.rentme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.repository.base.BaseRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, BaseRepository {

    Advertisement findByIdAndDeletedFalse(Long id);



}
