package uz.unicorn.rentme.config.security.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.unicorn.rentme.entity.AuthUser;
import uz.unicorn.rentme.entity.base.Auditable;
import uz.unicorn.rentme.repository.AuthUserRepository;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public record UtilsForSessionUser(AuthUserRepository repository) {

    public Long getSessionId() {
        Optional<AuthUser> byPhoneNumber = repository
                .findByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return byPhoneNumber.map(Auditable::getId).orElse(null);
    }

}

