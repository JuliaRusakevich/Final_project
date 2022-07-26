package com.gmail.juliarusakevich.classifier.service.mapper;

import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryReadDTO;
import com.gmail.juliarusakevich.classifier.entity.ConcertCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface ConcertCategoryMapper {

    ConcertCategoryReadDTO toDTO(ConcertCategory user);

    @Mapping(source = "dtCreate", target = "dtCreate", defaultExpression = "java(LocalDateTime.now())")
    ConcertCategory toEntity(ConcertCategoryCreateDTO object);
}
