package uz.unicorn.rentme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.unicorn.rentme.dto.brand.BrandCreateDTO;
import uz.unicorn.rentme.dto.brand.BrandDTO;
import uz.unicorn.rentme.dto.brand.BrandUpdateDTO;
import uz.unicorn.rentme.entity.Brand;
import uz.unicorn.rentme.mapper.base.GenericMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring",uses = {TransportModelMapper.class})
public interface BrandMapper extends GenericMapper<Brand, BrandDTO, BrandCreateDTO, BrandUpdateDTO> {
    @Override
    Brand fromDTO(BrandDTO dto);

    @Override
    List<Brand> fromDTO(List<BrandDTO> dtoList);

    @Override
    BrandDTO toDTO(Brand entity);

    @Override
    List<BrandDTO> toDTO(List<Brand> entities);

    @Override
    Brand fromUpdateDTO(BrandUpdateDTO dto,@MappingTarget Brand entity);

    @Override
    Brand fromCreateDTO(BrandCreateDTO dto);
}
