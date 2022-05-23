package uz.unicorn.rentme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.unicorn.rentme.entity.TransportType;
import uz.unicorn.rentme.enums.AdvertisementCategory;
import uz.unicorn.rentme.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface TransportTypeRepository extends JpaRepository<TransportType, Long>, BaseRepository {

    @Query(nativeQuery = true, value ="select ")
    Optional<List<TransportType>> findByCategory(AdvertisementCategory category);

    Optional<List<String>> findByNameStartingWith(String startName);


}
