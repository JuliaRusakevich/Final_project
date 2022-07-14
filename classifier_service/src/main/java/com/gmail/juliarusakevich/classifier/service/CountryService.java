package com.gmail.juliarusakevich.classifier.service;

import com.gmail.juliarusakevich.classifier.repository.ICountryRepository;
import com.gmail.juliarusakevich.classifier.dto.country.CountryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.country.CountryReadDTO;
import com.gmail.juliarusakevich.classifier.model.Country;
import com.gmail.juliarusakevich.classifier.service.api.ICountry;
import com.gmail.juliarusakevich.classifier.service.mapper.CountryMapper;
import com.gmail.juliarusakevich.classifier.validator.api.IValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CountryService implements ICountry {

    private final ICountryRepository repository;
    private final CountryMapper mapper;
    private final IValidator<CountryCreateDTO> validator;


    public CountryService(ICountryRepository repository,
                          CountryMapper mapper,
                          IValidator<CountryCreateDTO> validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public Country create(CountryCreateDTO dto) {
        var county = mapper.toEntity(dto);
        return this.repository.save(county);
     }

    @Override
    public Page<CountryReadDTO> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(mapper::toDTO);
    }

}
