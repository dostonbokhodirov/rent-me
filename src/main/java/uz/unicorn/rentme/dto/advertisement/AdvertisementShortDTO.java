package uz.unicorn.rentme.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.dto.price.PriceDTO;
import uz.unicorn.rentme.dto.transport.TransportDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementShortDTO extends GenericDTO {

    private String description;
    private List<PriceDTO> prices;
    private AdvertisementCategory category;
    private TransportDTO transport;

}
