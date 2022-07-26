package com.gmail.juliarusakevich.classifier.controller;

import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryReadDTO;
import com.gmail.juliarusakevich.classifier.entity.ConcertCategory;
import com.gmail.juliarusakevich.classifier.pagination.PageResponse;
import com.gmail.juliarusakevich.classifier.service.api.IClassifierService;
import com.gmail.juliarusakevich.classifier.service.mapper.ConcertCategoryMapper;
import com.gmail.juliarusakevich.classifier.validator.api.IValidator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ConcertCategoryController {

    private final IClassifierService<ConcertCategory, ConcertCategoryReadDTO> service;
    private final IValidator<ConcertCategoryCreateDTO> validator;
    private final ConcertCategoryMapper mapper;

    public ConcertCategoryController(
            IClassifierService<ConcertCategory, ConcertCategoryReadDTO> service,
            IValidator<ConcertCategoryCreateDTO> validator,
            ConcertCategoryMapper mapper) {
        this.service = service;
        this.validator = validator;
        this.mapper = mapper;
    }

    @RequestMapping(value = "/api/v1/classifier/concert/category", method = RequestMethod.POST)
    public ConcertCategory add(@RequestBody ConcertCategoryCreateDTO dto) {
        this.validator.isValid(dto);
        var concertCategory = this.mapper.toEntity(dto);
        return this.service.add(concertCategory);
    }


    @RequestMapping(value = "/api/v1/classifier/concert/category", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ConcertCategoryReadDTO> findAll(Pageable pageable) {
        var result = this.service.findAll(pageable);
        return PageResponse.of(result);
    }

    @RequestMapping(value = "/api/v1/classifier/concert/{uuid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ConcertCategoryReadDTO findCountryByUUID(@PathVariable UUID uuid) {
        return this.service.findByUUID(uuid);
    }

}
