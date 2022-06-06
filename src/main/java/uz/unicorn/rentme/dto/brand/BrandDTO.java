package uz.unicorn.rentme.dto.brand;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.dto.transportModel.TransportModelDTO;

import java.util.List;
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandDTO extends GenericDTO {
    private String image;
    private String name;
    private List<TransportModelDTO> models;
}
