package uz.unicorn.rentme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.unicorn.rentme.dto.transport.TransportCreateDTO;
import uz.unicorn.rentme.dto.transport.TransportDTO;
import uz.unicorn.rentme.dto.transport.TransportUpdateDTO;
import uz.unicorn.rentme.entity.Transport;
import uz.unicorn.rentme.mapper.base.GenericMapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {PictureMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface TransportMapper extends GenericMapper<Transport, TransportDTO, TransportCreateDTO, TransportUpdateDTO> {
    @Override
    Transport fromDTO(TransportDTO dto);

    @Override
    List<Transport> fromDTO(List<TransportDTO> dtoList);

    @Override
    TransportDTO toDTO(Transport entity);

    @Override
    List<TransportDTO> toDTO(List<Transport> entities);

    @Override
    Transport fromUpdateDTO(TransportUpdateDTO dto, @MappingTarget Transport entity);

    @Override
    Transport fromCreateDTO(TransportCreateDTO dto);
}
