package uz.unicorn.rentme.entity;

import lombok.*;
import uz.unicorn.rentme.enums.PriceType;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PriceType type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Advertisement advertisement;

}
