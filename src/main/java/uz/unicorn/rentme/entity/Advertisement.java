package uz.unicorn.rentme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.Point;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Advertisement extends Auditable {

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private AdvertisementCategory category;

    @Column(nullable = false)
    private Point location;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private Long minDuration;

    @Column(nullable = false)
    private Long maxDuration;

    @OneToOne
    private Transport transport;

    @OneToMany(mappedBy = "advertisement")
    List<Picture> pictures;

}
