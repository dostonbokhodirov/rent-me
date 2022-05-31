package uz.unicorn.rentme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import uz.unicorn.rentme.dto.location.LocationCreateDTO;
import uz.unicorn.rentme.dto.location.LocationDto;
import uz.unicorn.rentme.dto.location.LocationUpdateDTO;
import uz.unicorn.rentme.entity.Location;
import uz.unicorn.rentme.mapper.base.GenericMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper extends GenericMapper<Location, LocationDto, LocationCreateDTO, LocationUpdateDTO> {
    @Override
    Location fromDTO(LocationDto dto);

    @Override
    List<Location> fromDTO(List<LocationDto> dtoList);

    @Override
    LocationDto toDTO(Location entity);

    @Override
    List<LocationDto> toDTO(List<Location> entities);

    @Override
    Location fromUpdateDTO(LocationUpdateDTO dto,@MappingTarget Location entity);

    @Override
    Location fromCreateDTO(LocationCreateDTO dto);
}
