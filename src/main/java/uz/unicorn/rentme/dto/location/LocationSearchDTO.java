package uz.unicorn.rentme.dto.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.dto.base.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationSearchDTO implements BaseDTO {
    private String name;
    private Double longitude;
    private Double latitude;
    private Long distance = 10L;
}
