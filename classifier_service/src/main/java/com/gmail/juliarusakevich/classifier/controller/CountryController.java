package com.gmail.juliarusakevich.classifier.controller;

import com.gmail.juliarusakevich.classifier.dto.country.CountryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.country.CountryReadDTO;
import com.gmail.juliarusakevich.classifier.model.Country;
import com.gmail.juliarusakevich.classifier.pagination.PageResponse;
import com.gmail.juliarusakevich.classifier.service.api.ICountry;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountryController {

    private final ICountry service;

    public CountryController(ICountry service) {
        this.service = service;
    }

    /*
    {
  "title": "RUS",
  "description": "Россия"
}
    */

    @RequestMapping(value = "/api/v1/classifier/country", method = RequestMethod.POST)
    public ResponseEntity<Country> addCountry(@RequestBody CountryCreateDTO dto) {
        if (this.service.create(dto) != null) {
            return new ResponseEntity<>(this.service.create(dto), HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @RequestMapping(value = "/api/v1/classifier/country", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<CountryReadDTO> findAll(Pageable pageable) {
        var result = this.service.findAll(pageable);
        return PageResponse.of(result);
    }
}
