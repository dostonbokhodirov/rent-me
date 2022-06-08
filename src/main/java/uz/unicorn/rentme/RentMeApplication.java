package uz.unicorn.rentme;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.unicorn.rentme.entity.base.SecurityAuditorAware;
import uz.unicorn.rentme.property.OpenApiProperties;
import uz.unicorn.rentme.property.ServerProperties;

@SpringBootApplication
@OpenAPIDefinition
@EnableConfigurationProperties(
        {ServerProperties.class,
                OpenApiProperties.class}
)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class RentMeApplication {


    public static void main(String[] args) {
        SpringApplication.run(RentMeApplication.class, args);
    }

    @Bean
    AuditorAware<Long> auditorAware() {
        return new SecurityAuditorAware();
    }

}
