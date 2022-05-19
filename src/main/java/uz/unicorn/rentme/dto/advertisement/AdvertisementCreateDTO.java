package uz.unicorn.rentme.dto.advertisement;

import lombok.*;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.geo.Point;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.dto.transport.TransportCreateDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject
public class AdvertisementCreateDTO implements BaseDTO {

    private String title;

    private String description;

    private Long price;

    private AdvertisementCategory category;

    private Point location;

    private LocalDateTime startDate;

    private Long minDuration;

    private Long maxDuration;

    private TransportCreateDTO transport;

}
