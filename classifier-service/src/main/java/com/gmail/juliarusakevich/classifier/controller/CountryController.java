package com.gmail.juliarusakevich.classifier.controller;


import com.gmail.juliarusakevich.classifier.dto.country.CountryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.country.CountryReadDTO;
import com.gmail.juliarusakevich.classifier.entity.Country;
import com.gmail.juliarusakevich.classifier.pagination.PageResponse;
import com.gmail.juliarusakevich.classifier.service.api.IClassifierService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
http://localhost:8080/v3/api-docs/
http://localhost:8080/swagger-ui/index.html
 */
@RestController
@RequestMapping("/api/v1/classifier/country")
public class CountryController {

    private final IClassifierService<Country, CountryReadDTO, CountryCreateDTO> service;

    public CountryController(IClassifierService<Country, CountryReadDTO, CountryCreateDTO> service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CountryReadDTO add(@RequestBody CountryCreateDTO dto) {
        return this.service.add(dto);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<CountryReadDTO> findAll(Pageable pageable) {
        var result = this.service.findAll(pageable);
        return PageResponse.of(result);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CountryReadDTO findCountryByUUID(@PathVariable UUID uuid) {
        return this.service.findByUUID(uuid);
    }
}
