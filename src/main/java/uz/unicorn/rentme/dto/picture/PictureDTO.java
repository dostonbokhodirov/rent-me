package uz.unicorn.rentme.dto.picture;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.unicorn.rentme.dto.base.BaseDTO;

@Getter
@Setter
@Builder
public class PictureDTO implements BaseDTO {

    private String path;

    private Boolean main;

}
