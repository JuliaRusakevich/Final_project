package com.gmail.juliarusakevich.classifier.service.mapper;

import com.gmail.juliarusakevich.classifier.dto.country.CountryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.country.CountryReadDTO;
import com.gmail.juliarusakevich.classifier.entity.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryReadDTO toDTO(Country object);
    Country toEntity(CountryCreateDTO dto);
}
