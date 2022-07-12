package com.gmail.juliarusakevich.classifier.service;

import com.gmail.juliarusakevich.classifier.repository.ICountryRepository;
import com.gmail.juliarusakevich.classifier.dto.CountryCreateDto;
import com.gmail.juliarusakevich.classifier.dto.CountryReadDto;
import com.gmail.juliarusakevich.classifier.model.Country;
import com.gmail.juliarusakevich.classifier.service.api.ICountry;
import com.gmail.juliarusakevich.classifier.service.mapper.CountryReadMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class CountryService implements ICountry {

    private final ICountryRepository repository;
    private final CountryReadMapper countryReadMapper;

    public CountryService(ICountryRepository repository, CountryReadMapper countryReadMapper) {
        this.repository = repository;
        this.countryReadMapper = countryReadMapper;
    }

    @Override
    public Country create(CountryCreateDto object) {
        return this.repository.save(Country.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .title(object.getTitle())
                .description(object.getDescription())
                .build());

    }

    @Override
    public Page<CountryReadDto> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(countryReadMapper::mapFrom);
    }

    @Override
    public List<CountryReadDto> readAll() {
        return this.repository.findAll()
                .stream()
                .map(countryReadMapper::mapFrom)
                .sorted(comparing(CountryReadDto::getUuid))
                .collect(Collectors.toList());
    }
}
