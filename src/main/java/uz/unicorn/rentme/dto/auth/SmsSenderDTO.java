package uz.unicorn.rentme.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsSenderDTO {
    private String recipients;
    private String originator;
    private String body;
}
