package com.gmail.juliarusakevich.users.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gmail.juliarusakevich.users.controller.utils.json.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;

import java.time.LocalDateTime;
import java.util.Map;

@Configuration
public class ControllerConfig {

    @Bean
    public Jackson2ObjectMapperFactoryBean objectMapper() {
        Jackson2ObjectMapperFactoryBean mapper = new Jackson2ObjectMapperFactoryBean();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        mapper.setModulesToInstall(JavaTimeModule.class);
        mapper.setSerializersByType(Map.of(LocalDateTime.class, new LocalDateTimeSerializer()));
        return mapper;
    }
}
