package uz.unicorn.rentme.entity.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import uz.unicorn.rentme.entity.AuthUser;
import uz.unicorn.rentme.exceptions.NotFoundException;
import uz.unicorn.rentme.repository.AuthUserRepository;

import java.util.Collections;
import java.util.Optional;

@Component
public class SecurityAuditorAware implements AuditorAware<String> {

    @Autowired
    private AuthUserRepository repository;

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        AuthUser user = repository.findByPhoneNumber(authentication.getPrincipal().toString()).orElseThrow(() -> new NotFoundException("User not found"));
        UserDetails userDetails = User.builder()
                .username(user.getPhoneNumber())
                .password("password")
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole())))
                .build();
        return Optional.of(userDetails.toString());
    }
}
