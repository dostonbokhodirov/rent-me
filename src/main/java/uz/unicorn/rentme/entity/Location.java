package uz.unicorn.rentme.entity;

import lombok.*;
import org.hibernate.annotations.Where;
import uz.unicorn.rentme.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Where(clause = "deleted is false")
public class Location extends Auditable {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;
}
