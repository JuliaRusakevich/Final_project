package com.gmail.juliarusakevich.classifier.service.mapper;

import com.gmail.juliarusakevich.classifier.dto.ConcertCategoryReadDto;
import com.gmail.juliarusakevich.classifier.model.ConcertCategory;
import org.springframework.stereotype.Component;

@Component
public class ConcertCategoryReadMapper {

    public ConcertCategoryReadDto mapFrom(ConcertCategory object) {
        return ConcertCategoryReadDto.builder()
                .uuid(object.getUuid())
                .dtCreate(object.getDtCreate())
                .dtCreate(object.getDtCreate())
                .title(object.getTitle())
                .build();
    }
}
