package uz.unicorn.rentme.entity;

import lombok.*;
import org.hibernate.annotations.Where;
import uz.unicorn.rentme.entity.base.Auditable;
import org.springframework.data.geo.Point;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "deleted is false")
public class Location extends Auditable {
    private String address;
    private Point location;
}
