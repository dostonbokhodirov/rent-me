package uz.unicorn.rentme.dto.picture;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.unicorn.rentme.dto.base.GenericDTO;
@Getter
@Setter
@Builder
public class PictureUpdateDto extends GenericDTO {
    private String path;

    private Boolean main;
}
