package uz.unicorn.rentme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import uz.unicorn.rentme.dto.location.LocationCreateDTO;
import uz.unicorn.rentme.dto.location.LocationDTO;
import uz.unicorn.rentme.dto.location.LocationUpdateDTO;
import uz.unicorn.rentme.entity.Location;
import uz.unicorn.rentme.mapper.base.GenericMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper extends GenericMapper<Location, LocationDTO, LocationCreateDTO, LocationUpdateDTO> {
    @Override
    Location fromDTO(LocationDTO dto);

    @Override
    List<Location> fromDTO(List<LocationDTO> dtoList);

    @Override
    LocationDTO toDTO(Location entity);

    @Override
    List<LocationDTO> toDTO(List<Location> entities);

    @Override
    Location fromUpdateDTO(LocationUpdateDTO dto,@MappingTarget Location entity);

    @Override
    Location fromCreateDTO(LocationCreateDTO dto);
}
