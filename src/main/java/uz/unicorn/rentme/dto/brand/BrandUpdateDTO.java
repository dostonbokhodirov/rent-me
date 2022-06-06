package uz.unicorn.rentme.dto.brand;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.unicorn.rentme.dto.base.GenericDTO;
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandUpdateDTO extends GenericDTO {
    private String image;
    private String name;
}
