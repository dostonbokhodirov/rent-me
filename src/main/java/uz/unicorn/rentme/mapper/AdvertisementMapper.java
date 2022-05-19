package uz.unicorn.rentme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.unicorn.rentme.dto.advertisement.AdvertisementCreateDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementUpdateDTO;
import uz.unicorn.rentme.dto.auth.AuthUserUpdateDTO;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.mapper.base.GenericMapper;

import javax.persistence.MappedSuperclass;
import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
uses = {TransportMapper.class})
public interface AdvertisementMapper extends GenericMapper<Advertisement, AdvertisementDTO, AdvertisementCreateDTO, AdvertisementUpdateDTO> {

    @Override
    Advertisement fromDTO(AdvertisementDTO dto);

    @Override
    List<Advertisement> fromDTO(List<AdvertisementDTO> dtoList);

    @Override
    AdvertisementDTO toDTO(Advertisement entity);

    @Override
    List<AdvertisementDTO> toDTO(List<Advertisement> entities);

    @Override
    Advertisement fromUpdateDTO(AdvertisementUpdateDTO dto, @MappingTarget Advertisement entity);

    @Override
    Advertisement fromCreateDTO(AdvertisementCreateDTO dto);

}
