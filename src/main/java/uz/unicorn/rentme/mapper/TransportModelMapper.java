package uz.unicorn.rentme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.unicorn.rentme.dto.transportModel.TransportModelCreateDTO;
import uz.unicorn.rentme.dto.transportModel.TransportModelDTO;
import uz.unicorn.rentme.dto.transportModel.TransportModelUpdateDTO;
import uz.unicorn.rentme.entity.TransportModel;
import uz.unicorn.rentme.mapper.base.GenericMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface TransportModelMapper extends
        GenericMapper<
                TransportModel,
                TransportModelDTO,
                TransportModelCreateDTO,
                TransportModelUpdateDTO> {
    @Override
    TransportModel fromDTO(TransportModelDTO dto);

    @Override
    List<TransportModel> fromDTO(List<TransportModelDTO> dtoList);

    @Override
    TransportModelDTO toDTO(TransportModel entity);

    @Override
    List<TransportModelDTO> toDTO(List<TransportModel> entities);

    @Override
    TransportModel fromUpdateDTO(TransportModelUpdateDTO dto, @MappingTarget TransportModel entity);

    @Override
    @Mapping(target = "brand",ignore=true)
    TransportModel fromCreateDTO(TransportModelCreateDTO dto);
}
