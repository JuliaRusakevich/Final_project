package com.gmail.juliarusakevich.event.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.gmail.juliarusakevich.event.controller.json.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

@Configuration
public class ControllerConfig implements WebMvcConfigurer {

    @Bean
    public Jackson2ObjectMapperFactoryBean objectMapper() {
        Jackson2ObjectMapperFactoryBean mapper = new Jackson2ObjectMapperFactoryBean();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        mapper.setModulesToInstall(JavaTimeModule.class);
        mapper.setSerializersByType(Map.of(LocalDateTime.class, new LocalDateTimeSerializer()));
        //ДЛЯ "release_date": "30 августа 2022",
        mapper.setSerializers(new LocalDateSerializer(
                DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"))));
        mapper.setDeserializers(new LocalDateDeserializer(
                DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"))));

        return mapper;
    }
}