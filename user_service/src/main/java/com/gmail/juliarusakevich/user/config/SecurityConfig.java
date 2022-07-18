package com.gmail.juliarusakevich.user.config;

import com.gmail.juliarusakevich.user.controller.filter.JwtFilter;
import com.gmail.juliarusakevich.user.repository.model.enums.UserRole;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter filter;

    public SecurityConfig(JwtFilter filter) {
        this.filter = filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(urlConfig -> urlConfig
                        .antMatchers("/api/v1/users/login",
                                "/api/v1/users/registration")
                        .permitAll()

                        .antMatchers("/api/v1/users/me", "/api/v1/users/logout").authenticated()


                        .antMatchers("/api/v1/users",
                                "/api/v1/users/{uuid}",
                                "/api/v1/users/{uuid}/dt_update/{dtUpdate}")
                        .hasAuthority(UserRole.ADMIN.getAuthority())
                        .anyRequest().authenticated()//любое обращение, только с аутентиф пользователем
                )
                .logout(logout -> logout
                        .logoutUrl("/api/v1/users/logout")
                        .logoutSuccessUrl("/api/v1/users/login")
                        .deleteCookies("JSESSIONID"))

                //.formLogin(login -> login
                //        .loginPage("/api/v1/users/login")
                //        .permitAll());

                //.httpBasic(Customizer.withDefaults())

                .addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class);
    }

}

