package uz.unicorn.rentme.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OtpUtils {

    @Value(value = "${otp.url}")
    private String baseUrl;

    @Value(value = "${otp.authorization}")
    private String authorization;

    @Value(value = "${otp.expiry}")
    private int expiry;

    public int randomCode() {
        int min = 10000;
        int max = 99999;
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }

}
