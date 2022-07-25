package com.gmail.juliarusakevich.users.config;


import com.gmail.juliarusakevich.users.controller.filter.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
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
        http
                // Our public endpoints
                .csrf().disable()
                .authorizeRequests(urlConfig -> urlConfig

                        .antMatchers("/api/v1/users/login",
                                "/api/v1/users/registration")
                        .permitAll()

                        .antMatchers( "/api/v1/users/me","/api/v1/users/logout")
                        .authenticated()

                        .antMatchers("/user/*").authenticated()
                        .antMatchers("/admin/*").hasRole("ADMIN")

                        .antMatchers("/api/v1/users",
                                "/api/v1/users/{uuid}",
                                "/api/v1/users/{uuid}/dt_update/{dtUpdate}")

                        .hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())

                .logout(logout -> logout
                        .logoutUrl("/api/v1/users/logout")
                        .logoutSuccessUrl("/api/v1/users/login")
                        .deleteCookies("JSESSIONID"));
// без токена нельзя
        /*
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
                        //.hasAuthority(UserRole.ADMIN.getAuthority())
                        .hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()//любое обращение, только с аутентиф пользователем
                )
                .logout(logout -> logout
                        .logoutUrl("/api/v1/users/logout")
                        .logoutSuccessUrl("/api/v1/users/login")
                        .deleteCookies("JSESSIONID"));

         */

        // Add JWT token filter
        /*
        Добавление фильтра, который разбирает токен и превращает  в пользователя
         */
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );
    }


}
/*
private final JwtFilter jwtFilter;

    public WebSecurity(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers("/user/*").hasRole("USER")
                .antMatchers("/register", "/auth").permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
 */