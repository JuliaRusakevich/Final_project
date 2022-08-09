package com.gmail.juliarusakevich.users.config;


import com.gmail.juliarusakevich.users.controller.filter.JwtFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
@ComponentScan("com.gmail.juliarusakevich.users.controller.filter")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter filter;

    public WebSecurityConfig(JwtFilter filter) {
        this.filter = filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        /*
        Что должно произойти, если пытаются зайти на url,
        туда куда нужно прийти с токеном, а пришли без него
        Отвечаем, если не мкахали логин и пароль
         */
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();

        // Set permissions on endpoints
        /*
         ** - любой url начинающийся /public/
         * permitAll() - можно всем без токена
         */

        http.authorizeRequests()
                .antMatchers("/api/v1/users/login").permitAll()
                .antMatchers("/api/v1/users/registration").permitAll()
                .antMatchers("/api/v1/users/me").authenticated()
                .antMatchers("/api/v1/users").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/v1/users/{uuid}").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/v1/users/{uuid}").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/v1/users/status/{uuid}/version/{version}").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/v1/users//{uuid}/version/{version}").hasAuthority("ROLE_ADMIN");


        // Add JWT token filter
        /*
        Добавление фильтра, который разбирает токен и превращает в пользователя
         */
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );
    }
}
