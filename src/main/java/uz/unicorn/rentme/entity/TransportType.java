package uz.unicorn.rentme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.entity.base.Auditable;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TransportType  extends Auditable {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AdvertisementCategory category;

    @Column(nullable = false)
    private String imagePath;

    @OneToMany(mappedBy = "type",cascade = CascadeType.ALL)
    private List<Transport> transports;
}
