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
@RequestMapping("/api/v1/classifier/concert/category")
public class ConcertCategoryController {

    public ConcertCategoryController(IClassifierService<ConcertCategory, ConcertCategoryReadDTO, ConcertCategoryCreateDTO> service) {
        this.service = service;
    }

    private final IClassifierService<ConcertCategory, ConcertCategoryReadDTO, ConcertCategoryCreateDTO> service;


    @RequestMapping(method = RequestMethod.POST)
    public ConcertCategoryReadDTO add(@RequestBody ConcertCategoryCreateDTO dto) {
        return this.service.add(dto);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ConcertCategoryReadDTO> findAll(Pageable pageable) {
        var result = this.service.findAll(pageable);
        return PageResponse.of(result);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ConcertCategoryReadDTO findCountryByUUID(@PathVariable UUID uuid) {
        return this.service.findByUUID(uuid);
    }

}
