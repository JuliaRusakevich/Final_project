package com.gmail.juliarusakevich.classifier.service.api;

import com.gmail.juliarusakevich.classifier.dto.country.CountryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.country.CountryReadDTO;
import com.gmail.juliarusakevich.classifier.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICountry {

    Country create(CountryCreateDTO object);

    Page<CountryReadDTO> findAll(Pageable pageable);
}
