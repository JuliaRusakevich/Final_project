package com.gmail.juliarusakevich.classifier.service.mapper;

import com.gmail.juliarusakevich.classifier.dto.CountryReadDto;
import com.gmail.juliarusakevich.classifier.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryReadMapper {

    public CountryReadDto mapFrom(Country object) {
        return CountryReadDto.builder()
                .uuid(object.getUuid())
                .dtCreate(object.getDtCreate())
                .dtUpdate(object.getDtUpdate())
                .title(object.getTitle())
                .description(object.getDescription())
                .build();
    }
}
