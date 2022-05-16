package uz.unicorn.rentme.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.geo.Point;
import uz.unicorn.rentme.dto.base.GenericDTO;
import uz.unicorn.rentme.dto.transport.TransportDTO;
import uz.unicorn.rentme.entity.Transport;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementDTO extends GenericDTO {

    private String title;

    private String description;

    private Long price;

    private AdvertisementCategory category;

    private Point location;

    private LocalDate startDate;

    private Long minDuration;

    private Long maxDuration;

    private TransportDTO transport;

    private LocalDateTime createdAt;

    private Long createdBy;

    private LocalDateTime updatedAt;

    private Long updatedBy;

}
