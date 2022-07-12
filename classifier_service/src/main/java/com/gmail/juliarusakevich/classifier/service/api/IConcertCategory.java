package com.gmail.juliarusakevich.classifier.service.api;

import com.gmail.juliarusakevich.classifier.dto.ConcertCategoryCreateDto;
import com.gmail.juliarusakevich.classifier.dto.ConcertCategoryReadDto;
import com.gmail.juliarusakevich.classifier.model.ConcertCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IConcertCategory {

    ConcertCategory create(ConcertCategoryCreateDto object);

    Page<ConcertCategoryReadDto> findAll(Pageable pageable);

}
