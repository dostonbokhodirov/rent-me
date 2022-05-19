package uz.unicorn.rentme;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uz.unicorn.rentme.dto.auth.AuthUserCreateDTO;
import uz.unicorn.rentme.entity.base.SecurityAuditorAware;
import uz.unicorn.rentme.property.OpenApiProperties;
import uz.unicorn.rentme.property.ServerProperties;
import uz.unicorn.rentme.service.auth.AuthUserService;

@SpringBootApplication
@OpenAPIDefinition
@EnableConfigurationProperties(
        {ServerProperties.class,
                OpenApiProperties.class}
)
@RequiredArgsConstructor
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories
public class RentMeApplication {
    private final AuthUserService authUserService;

    public static void main(String[] args) {
        SpringApplication.run(RentMeApplication.class, args);
    }

    //    @Bean
    CommandLineRunner run() {
        return args -> {
            AuthUserCreateDTO dto = AuthUserCreateDTO
                    .builder()
                    .firstName("Admin")
                    .lastName("Root")
                    .phoneNumber("+998901234567")
                    .build();
            authUserService.create(dto);
        };
    }

    @Bean
    AuditorAware<Long> auditorAware() {
        return new SecurityAuditorAware();
    }


}
