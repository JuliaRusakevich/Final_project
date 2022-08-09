package com.gmail.juliarusakevich.event.config;

import com.gmail.juliarusakevich.event.service.UserHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class AuditConfig {

    private final UserHolder userHolder;

    public AuditConfig(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(userHolder.getUser().getUsername());
    }

}
