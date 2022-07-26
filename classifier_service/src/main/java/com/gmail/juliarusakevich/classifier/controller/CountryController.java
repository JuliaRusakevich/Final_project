package com.gmail.juliarusakevich.classifier.controller;


import com.gmail.juliarusakevich.classifier.dto.country.CountryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.country.CountryReadDTO;
import com.gmail.juliarusakevich.classifier.entity.Country;
import com.gmail.juliarusakevich.classifier.pagination.PageResponse;
import com.gmail.juliarusakevich.classifier.service.api.IClassifierService;
import com.gmail.juliarusakevich.classifier.service.mapper.CountryMapper;
import com.gmail.juliarusakevich.classifier.validator.ValidationException;
import com.gmail.juliarusakevich.classifier.validator.api.IValidator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
http://localhost:8080/v3/api-docs/
http://localhost:8080/swagger-ui/index.html
 */
@RestController
public class CountryController {

    private final IClassifierService<Country, CountryReadDTO> service;
    // private final CountryService service;
    private final IValidator<CountryCreateDTO> validator;
    private final CountryMapper mapper;

    public CountryController(
            IClassifierService<Country, CountryReadDTO> service,
            IValidator<CountryCreateDTO> validator,
            CountryMapper mapper) {
        this.service = service;
        this.validator = validator;
        this.mapper = mapper;
    }

    /*
    {
  "title": "RUS",
  "description": "Россия"
}
    */

    @RequestMapping(value = "/api/v1/classifier/country", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Country add(@RequestBody CountryCreateDTO dto) {
        var validationResult = this.validator.isValid(dto);
        if (validationResult.isValid()) {
            var country = this.mapper.toEntity(dto);
            return this.service.add(country);
        } else {
            throw new ValidationException(validationResult.getErrors());
        }
    }


    @RequestMapping(value = "/api/v1/classifier/country", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<CountryReadDTO> findAll(Pageable pageable) {
        var result = this.service.findAll(pageable);
        return PageResponse.of(result);
    }

    @RequestMapping(value = "/api/v1/classifier/country/{uuid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CountryReadDTO findCountryByUUID(@PathVariable UUID uuid) {
        return this.service.findByUUID(uuid);
    }
}
