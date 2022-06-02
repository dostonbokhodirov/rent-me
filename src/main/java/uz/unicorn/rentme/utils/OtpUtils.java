package uz.unicorn.rentme.utils;

public class OtpUtils {

    public static final String baseUrl = "https://rest.messagebird.com/messages";
    public static final String authorization = "AccessKey xsTieTlZpZJjxyexNB2LK8PzJ";
    public static final int expiry = 10;

    public static int randomCode() {
        int min = 10000;
        int max = 99999;
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }

}
