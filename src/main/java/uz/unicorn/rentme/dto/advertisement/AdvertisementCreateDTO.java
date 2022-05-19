package uz.unicorn.rentme.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.geo.Point;
import uz.unicorn.rentme.dto.base.BaseDTO;
import uz.unicorn.rentme.dto.transport.TransportDTO;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementCreateDTO implements BaseDTO {
    private String title;
    private String description;
    private Long price;
    private String category;
    private Point location;
    private LocalDateTime startDate;
    private Long minDuration;
    private Long maxDuration;
    private TransportDTO transport;
}
