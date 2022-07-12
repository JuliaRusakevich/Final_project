package com.gmail.juliarusakevich.classifier.service.api;


import com.gmail.juliarusakevich.classifier.dto.CountryCreateDto;
import com.gmail.juliarusakevich.classifier.dto.CountryReadDto;
import com.gmail.juliarusakevich.classifier.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICountry {

    Country create(CountryCreateDto object);

    List<CountryReadDto> readAll();

    Page<CountryReadDto> findAll(Pageable pageable);
}
