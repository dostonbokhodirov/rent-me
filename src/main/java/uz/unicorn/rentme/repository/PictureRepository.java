package uz.unicorn.rentme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.unicorn.rentme.entity.Picture;
import uz.unicorn.rentme.repository.base.BaseRepository;

public interface PictureRepository extends JpaRepository<Picture, Long>, BaseRepository {

}
