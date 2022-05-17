package uz.unicorn.rentme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.repository.base.BaseRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, BaseRepository {

    @Query(value = "select a.* from Advertisement a where a.id = :id and a.deleted = false",nativeQuery = true)
    Advertisement findByIdAndDeletedFalse(@Param("id") Long id);



}
