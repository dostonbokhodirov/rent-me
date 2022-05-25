package uz.unicorn.rentme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.unicorn.rentme.entity.base.Auditable;
import uz.unicorn.rentme.enums.AdvertisementCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TransportType  extends Auditable {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AdvertisementCategory category;

    @Column(nullable = false)
    private String imagePath;
}
