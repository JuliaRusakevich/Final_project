package com.gmail.juliarusakevich.classifier.service;

import com.gmail.juliarusakevich.classifier.dto.country.CountryReadDTO;
import com.gmail.juliarusakevich.classifier.entity.Country;
import com.gmail.juliarusakevich.classifier.repository.ICountryRepository;
import com.gmail.juliarusakevich.classifier.service.api.IClassifierService;
import com.gmail.juliarusakevich.classifier.service.mapper.CountryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CountryService implements IClassifierService<Country, CountryReadDTO> {

    private final ICountryRepository repository;
    private final CountryMapper mapper;

    public CountryService(ICountryRepository repository, CountryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Country add(Country object) {
        return this.repository.save(object);
    }

    @Override
    public Page<CountryReadDTO> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(mapper::toDTO);
    }

    @Override
    public CountryReadDTO findByUUID(UUID uuid) {
        return this.repository.findById(uuid)
                .map(mapper::toDTO)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Country not found");
                });
    }
}
