package uz.unicorn.rentme.dto.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.enums.PriceType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceDTO extends GenericDTO {
    private Long quantity;
    private PriceType type;
}
