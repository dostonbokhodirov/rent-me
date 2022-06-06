package uz.unicorn.rentme.dto.brand;

import lombok.*;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.dto.transportModel.TransportModelCreateDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandCreateDTO implements BaseDTO {
    private String image;
    private String name;
    private List<TransportModelCreateDTO> models;
}
