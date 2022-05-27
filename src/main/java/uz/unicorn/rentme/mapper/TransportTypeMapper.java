package uz.unicorn.rentme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.unicorn.rentme.dto.transportType.TransportTypeCreateDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeUpdateDTO;
import uz.unicorn.rentme.entity.TransportModel;
import uz.unicorn.rentme.mapper.base.GenericMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface TransportTypeMapper extends
        GenericMapper<
                TransportModel,
                TransportTypeDTO,
                TransportTypeCreateDTO,
                TransportTypeUpdateDTO> {
    @Override
    TransportModel fromDTO(TransportTypeDTO dto);

    @Override
    List<TransportModel> fromDTO(List<TransportTypeDTO> dtoList);

    @Override
    TransportTypeDTO toDTO(TransportModel entity);

    @Override
    List<TransportTypeDTO> toDTO(List<TransportModel> entities);

    @Override
    TransportModel fromUpdateDTO(TransportTypeUpdateDTO dto, @MappingTarget TransportModel entity);

    @Override
    TransportModel fromCreateDTO(TransportTypeCreateDTO dto);
}
