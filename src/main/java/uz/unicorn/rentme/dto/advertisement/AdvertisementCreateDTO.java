package uz.unicorn.rentme.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.geo.Point;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.dto.transport.TransportCreateDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementCreateDTO implements BaseDTO {

    @NotBlank
    private String description;

    @NotBlank
    private Long price;

    @NotBlank
    private AdvertisementCategory category;

    @NotBlank
    private Point location;

//    @NotBlank
    private LocalDateTime startDate;

    @NotBlank
    private Long minDuration;

    @NotBlank
    private Long maxDuration;

    @NotBlank
    private TransportCreateDTO transport;

}
