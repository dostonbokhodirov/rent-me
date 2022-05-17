package uz.unicorn.rentme.utils;

public class OtpUtils {

    public static final String baseUrl = "https://rest.messagebird.com/messages";
    public static final String authorization = "AccessKey pX4Ti0YqeoTUeCzX9ezBFpWT1";
    public static final int expiry = 10;

    public static int randomCode(){
        int max = 999999;
        int min = 900000;
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }

}
