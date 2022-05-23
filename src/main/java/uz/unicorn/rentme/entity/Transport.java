package uz.unicorn.rentme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.entity.base.Auditable;
import uz.unicorn.rentme.enums.transport.TransportColor;
import uz.unicorn.rentme.enums.transport.TransportFuel;
import uz.unicorn.rentme.enums.transport.TransportTransmission;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transport extends Auditable {

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer year;

    @Column(columnDefinition = "varchar default 'MANUAL'")
    @Enumerated(value = EnumType.STRING)
    private TransportTransmission transmission;

    @Column(columnDefinition = "varchar default 'PETROL'")
    @Enumerated(value = EnumType.STRING)
    private TransportFuel fuelType;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TransportColor color;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private TransportType type;

    @OneToMany(mappedBy = "transport",cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @Column(columnDefinition = "bool default 'false'")
    private Boolean wellEquipped;

    @OneToOne(cascade = CascadeType.ALL)
    private Advertisement advertisement;

}
