package uz.unicorn.rentme.entity;

import lombok.*;
import org.springframework.data.geo.Point;
import uz.unicorn.rentme.entity.base.Auditable;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Advertisement extends Auditable {

    private String description;

    @Column(nullable = false)
    @OneToMany(mappedBy = "advertisement" ,cascade = CascadeType.ALL)
    private List<Price> prices;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AdvertisementCategory category;

    @Column(nullable = false)
    private Point location;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private int minDuration;

    @Column(nullable = false)
    private int maxDuration;

    @OneToOne(cascade = CascadeType.ALL)
    private Transport transport;
}
