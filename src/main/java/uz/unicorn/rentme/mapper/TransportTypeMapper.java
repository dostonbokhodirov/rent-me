package uz.unicorn.rentme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.unicorn.rentme.dto.transportType.TransportTypeCreateDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeUpdateDTO;
import uz.unicorn.rentme.entity.TransportType;
import uz.unicorn.rentme.mapper.base.GenericMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface TransportTypeMapper extends
        GenericMapper<
                TransportType,
                TransportTypeDTO,
                TransportTypeCreateDTO,
                TransportTypeUpdateDTO> {
    @Override
    TransportType fromDTO(TransportTypeDTO dto);

    @Override
    List<TransportType> fromDTO(List<TransportTypeDTO> dtoList);

    @Override
    TransportTypeDTO toDTO(TransportType entity);

    @Override
    List<TransportTypeDTO> toDTO(List<TransportType> entities);

    @Override
    TransportType fromUpdateDTO(TransportTypeUpdateDTO dto, @MappingTarget TransportType entity);

    @Override
    TransportType fromCreateDTO(TransportTypeCreateDTO dto);
}
