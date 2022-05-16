package uz.unicorn.rentme.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.unicorn.rentme.dto.advertisement.AdvertisementCreateDTO;
import uz.unicorn.rentme.dto.advertisement.AdvertisementDTO;
import uz.unicorn.rentme.dto.auth.AuthUserUpdateDTO;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.mapper.base.GenericMapper;

@Component
@Mapper(componentModel = "spring")
public interface AdvertisementMapper extends GenericMapper<Advertisement, AdvertisementDTO, AdvertisementCreateDTO, AuthUserUpdateDTO> {

}
