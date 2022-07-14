package com.gmail.juliarusakevich.classifier.service.api;

import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryReadDTO;
import com.gmail.juliarusakevich.classifier.model.ConcertCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IConcertCategory {

    ConcertCategory create(ConcertCategoryCreateDTO object);

    Page<ConcertCategoryReadDTO> findAll(Pageable pageable);

}
