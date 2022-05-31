package uz.unicorn.rentme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.unicorn.rentme.entity.TransportModel;
import uz.unicorn.rentme.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface TransportModelRepository extends JpaRepository<TransportModel, Long>, BaseRepository {

    Optional<List<TransportModel>> findByNameStartingWith(String startName);

    Optional<TransportModel> findByName(String name);

    @Query(value = "select t.name from TransportModel t ")
    List<String> findAllName();
}
