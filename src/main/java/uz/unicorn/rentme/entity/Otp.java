package uz.unicorn.rentme.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Where(clause = "deleted is false")
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDateTime expiry;

    @Column(nullable = false)
    private int code;

    public Otp(String phoneNumber, LocalDateTime expiry, int code) {
        this.phoneNumber = phoneNumber;
        this.expiry = expiry;
        this.code = code;
    }
}
