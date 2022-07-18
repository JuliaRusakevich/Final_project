package com.gmail.juliarusakevich.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@EnableJpaAuditing
//@EnableEnversRepositories(basePackageClasses = ApplicationRunner.class)
@Configuration
public class AuditConfig {

    //??????????? НЕ РАБОТАЕТ
    @Bean
    public AuditorAware<String> auditorAware() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return () -> Optional.ofNullable(auth)
                .map(authentication -> (UserDetails) authentication//можем привести к любому классу
                        .getPrincipal())
                .map(UserDetails::getUsername);

       // return ()-> Optional.of("julia");
    }

}
