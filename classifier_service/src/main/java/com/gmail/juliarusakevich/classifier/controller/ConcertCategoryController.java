package com.gmail.juliarusakevich.classifier.controller;

import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryReadDTO;
import com.gmail.juliarusakevich.classifier.model.ConcertCategory;
import com.gmail.juliarusakevich.classifier.pagination.PageResponse;
import com.gmail.juliarusakevich.classifier.service.api.IConcertCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConcertCategoryController {

    private final IConcertCategory service;

    public ConcertCategoryController(IConcertCategory service) {
        this.service = service;
    }

    @RequestMapping(value = "/api/v1/classifier/concert/category", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ConcertCategory addCategory(@RequestBody ConcertCategoryCreateDTO dto) {
        return this.service.create(dto);
    }

    @RequestMapping(value = "/api/v1/classifier/concert/category", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ConcertCategoryReadDTO> findAll(Pageable pageable) {
        var result = this.service.findAll(pageable);
        return PageResponse.of(result);
    }

}
