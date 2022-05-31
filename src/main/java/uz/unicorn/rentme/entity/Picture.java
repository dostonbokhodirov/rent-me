package uz.unicorn.rentme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import uz.unicorn.rentme.entity.base.Auditable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Where(clause = "deleted is false")
public class Picture extends Auditable {

    private String path;

    private Boolean main;

    @ManyToOne
    @JoinColumn
    private Transport transport;

}
