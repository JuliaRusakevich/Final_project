package com.gmail.juliarusakevich.classifier.service;

import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryReadDTO;
import com.gmail.juliarusakevich.classifier.model.ConcertCategory;
import com.gmail.juliarusakevich.classifier.repository.IConcertCategoryRepository;
import com.gmail.juliarusakevich.classifier.service.api.IConcertCategory;
import com.gmail.juliarusakevich.classifier.service.mapper.ConcertCategoryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConcertCategoryService implements IConcertCategory {

    private final IConcertCategoryRepository repository;
    private final ConcertCategoryMapper mapper;

    public ConcertCategoryService(
            IConcertCategoryRepository repository,
            ConcertCategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ConcertCategory create(ConcertCategoryCreateDTO dto) {
        var category = mapper.toEntity(dto);
        return this.repository.save(category);
    }

    @Override
    public Page<ConcertCategoryReadDTO> findAll(Pageable pageable) {
        var result = this.repository.findAll(pageable);
        return this.repository.findAll(pageable)
                .map(mapper::toDTO);
    }
}
