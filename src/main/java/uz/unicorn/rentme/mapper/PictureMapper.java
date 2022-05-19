package uz.unicorn.rentme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.unicorn.rentme.dto.picture.PictureCreateDto;
import uz.unicorn.rentme.dto.picture.PictureDTO;
import uz.unicorn.rentme.dto.picture.PictureUpdateDto;
import uz.unicorn.rentme.entity.Picture;
import uz.unicorn.rentme.mapper.base.GenericMapper;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface PictureMapper extends GenericMapper<Picture, PictureDTO, PictureCreateDto, PictureUpdateDto> {
    @Override
    Picture fromDTO(PictureDTO dto);

    @Override
    List<Picture> fromDTO(List<PictureDTO> dtoList);

    @Override
    PictureDTO toDTO(Picture entity);

    @Override
    List<PictureDTO> toDTO(List<Picture> entities);

    @Override
    Picture fromUpdateDTO(PictureUpdateDto dto,@MappingTarget Picture entity);

    @Override
    Picture fromCreateDTO(PictureCreateDto dto);
}
