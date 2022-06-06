package uz.unicorn.rentme.entity;

import lombok.*;
import org.hibernate.annotations.Where;
import uz.unicorn.rentme.entity.base.Auditable;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Where(clause = "deleted is false")
public class Advertisement extends Auditable {

    @Column
    private String description;

    @Column(nullable = false)
    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL)
    private List<Price> prices;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AdvertisementCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Location location;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private int minDuration;

    @Column(nullable = false)
    private int maxDuration;

    @OneToOne(cascade = CascadeType.ALL)
    private Transport transport;
}
