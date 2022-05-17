package uz.unicorn.rentme.dto.transport;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PictureDTO {

    private String path;

    private Boolean main;

}
