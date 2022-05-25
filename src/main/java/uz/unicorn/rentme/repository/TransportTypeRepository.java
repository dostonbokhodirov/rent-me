package uz.unicorn.rentme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.unicorn.rentme.entity.TransportType;
import uz.unicorn.rentme.enums.AdvertisementCategory;
import uz.unicorn.rentme.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface TransportTypeRepository extends JpaRepository<TransportType, Long>, BaseRepository {

    Optional<List<TransportType>> findByNameStartingWith(String startName);

    Optional<TransportType> findByName(String name);


}
