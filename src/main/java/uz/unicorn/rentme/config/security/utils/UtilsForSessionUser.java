package uz.unicorn.rentme.config.security.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.unicorn.rentme.repository.AuthUserRepository;


@Component
public record UtilsForSessionUser(AuthUserRepository service) {

    public Long getSessionId() {
        return service.getUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).orElse(null);
    }

}

