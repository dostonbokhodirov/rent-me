package uz.unicorn.rentme.dto.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.Point;
import uz.unicorn.rentme.dto.base.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationCreateDTO implements BaseDTO {
    private String address;
    private Point location;
}
