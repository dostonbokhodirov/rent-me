package uz.unicorn.rentme.config.security.utils;

public class SecurityUtils {

    public final static String[] WHITE_LIST = {
            "/error",
            "/auth/send-sms/**",
            "/api/login",
            "/auth/access-token",
            "/auth/refresh-token",
            "/auth/register",
            "/swagger-ui/**",
            "/api-docs/**",
            "/actuator/**",
            "/auth/create",
            "/main-page"
    };
}
