package com.gmail.juliarusakevich.users.config;

import com.gmail.juliarusakevich.users.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
@EnableJpaAuditing
@Configuration
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorAware() {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        return () -> Optional.ofNullable(auth)
                .map(authentication -> (UserDetails) authentication//можем привести к любому классу
                        .getPrincipal())
                .map(UserDetails::getUsername);

      // return ()-> Optional.of("julia");// если захардкодить, то работает
    }

}
