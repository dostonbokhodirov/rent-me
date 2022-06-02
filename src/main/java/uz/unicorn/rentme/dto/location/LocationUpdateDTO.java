package uz.unicorn.rentme.dto.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.Point;
import uz.unicorn.rentme.dto.base.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationUpdateDTO extends GenericDTO {
    private String name;
    private Double longitude;
    private Double latitude;
}
