package uz.unicorn.rentme.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.unicorn.rentme.dto.transport.TransportDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeCreateDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeDTO;
import uz.unicorn.rentme.dto.transportType.TransportTypeUpdateDTO;
import uz.unicorn.rentme.entity.TransportType;
import uz.unicorn.rentme.mapper.base.GenericMapper;
@Component
@Mapper(componentModel = "spring")
public interface TransportTypeMapper extends GenericMapper<TransportType, TransportTypeDTO, TransportTypeCreateDTO, TransportTypeUpdateDTO> {
}
