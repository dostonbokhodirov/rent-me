package uz.unicorn.rentme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.unicorn.rentme.dto.price.PriceCreateDTO;
import uz.unicorn.rentme.dto.price.PriceDTO;
import uz.unicorn.rentme.dto.price.PriceUpdateDTO;
import uz.unicorn.rentme.entity.Price;
import uz.unicorn.rentme.mapper.base.GenericMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PriceMapper extends GenericMapper<Price, PriceDTO, PriceCreateDTO, PriceUpdateDTO> {
    @Override
    Price fromDTO(PriceDTO dto);

    @Override
    List<Price> fromDTO(List<PriceDTO> dtoList);

    @Override
    PriceDTO toDTO(Price entity);

    @Override
    List<PriceDTO> toDTO(List<Price> entities);

    @Override
    Price fromUpdateDTO(PriceUpdateDTO dto,@MappingTarget Price entity);

    @Override
    Price fromCreateDTO(PriceCreateDTO dto);
}
