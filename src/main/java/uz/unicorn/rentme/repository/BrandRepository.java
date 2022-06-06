package uz.unicorn.rentme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.unicorn.rentme.entity.Brand;
import uz.unicorn.rentme.repository.base.BaseRepository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, BaseRepository {

    Optional<Brand> findByName(String brand);
}
