package com.gmail.juliarusakevich.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class UsersStorageConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource, PasswordEncoder encoder) {
        /*
        можно выбрать любой тип хранилища JdbcUserDetailsManager F4
        JDBC - DB или можно выбрать InMemoryJdbcUserDetailsManager
         */
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        /*
        создаем два пользователя
        ПАРОЛИ ХРАНИМ В ЗАШИФРОВАННОМ ВИДЕ - используем PasswordEncoder
         */



        try {
            UserDetails user = User.builder()
                    .username("user")
                    .password(encoder.encode("123"))
                    .roles("USER")
                    .build();
            UserDetails admin = User.builder()
                    .username("admin")
                    .password(encoder.encode("321"))
                    .roles("USER", "ADMIN")
                    .build();

            manager.createUser(user);
            manager.createUser(admin);
        } catch (DuplicateKeyException e) {
            //всё ок, уже есть
        }

        return manager;
    }
}
