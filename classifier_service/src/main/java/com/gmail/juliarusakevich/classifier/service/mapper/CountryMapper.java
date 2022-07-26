package com.gmail.juliarusakevich.classifier.service.mapper;

import com.gmail.juliarusakevich.classifier.dto.country.CountryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.country.CountryReadDTO;
import com.gmail.juliarusakevich.classifier.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface CountryMapper {

    CountryReadDTO toDTO(Country object);

    @Mapping(source = "dtCreate", target = "dtCreate", defaultExpression = "java(LocalDateTime.now())")
    Country toEntity(CountryCreateDTO dto);
}
