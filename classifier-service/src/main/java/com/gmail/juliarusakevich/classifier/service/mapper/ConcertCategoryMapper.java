package com.gmail.juliarusakevich.classifier.service.mapper;

import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryReadDTO;
import com.gmail.juliarusakevich.classifier.entity.ConcertCategory;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ConcertCategoryMapper {

    ConcertCategoryReadDTO toDTO(ConcertCategory user);

    ConcertCategory toEntity(ConcertCategoryCreateDTO object);
}
