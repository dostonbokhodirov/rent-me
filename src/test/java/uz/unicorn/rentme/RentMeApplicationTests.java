package uz.unicorn.rentme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class RentMeApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(LocalDateTime.now());
    }



}
