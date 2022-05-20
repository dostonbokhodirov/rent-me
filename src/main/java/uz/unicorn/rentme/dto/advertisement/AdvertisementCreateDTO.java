package uz.unicorn.rentme.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.geo.Point;
import org.springdoc.api.annotations.ParameterObject;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.dto.transport.TransportCreateDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ParameterObject
public class AdvertisementCreateDTO implements BaseDTO {
    private String description;
    private Long price;
    private AdvertisementCategory category;
    private Point location;
    private LocalDateTime startDate;
    private Long minDuration;
    private Long maxDuration;
    private TransportCreateDTO transport;
}
