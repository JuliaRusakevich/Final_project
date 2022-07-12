package com.gmail.juliarusakevich.classifier.service;

import com.gmail.juliarusakevich.classifier.dto.ConcertCategoryCreateDto;
import com.gmail.juliarusakevich.classifier.dto.ConcertCategoryReadDto;
import com.gmail.juliarusakevich.classifier.model.ConcertCategory;
import com.gmail.juliarusakevich.classifier.repository.IConcertCategoryRepository;
import com.gmail.juliarusakevich.classifier.service.api.IConcertCategory;
import com.gmail.juliarusakevich.classifier.service.mapper.ConcertCategoryReadMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConcertCategoryService implements IConcertCategory {

    private final IConcertCategoryRepository repository;
    private final ConcertCategoryReadMapper readMapper;

    public ConcertCategoryService(
            IConcertCategoryRepository repository,
            ConcertCategoryReadMapper readMapper) {
        this.repository = repository;
        this.readMapper = readMapper;
    }


    @Override
    public ConcertCategory create(ConcertCategoryCreateDto object) {
        return this.repository.save(ConcertCategory.builder()
                .uuid(UUID.randomUUID())
                .dt_create(LocalDateTime.now())
                .title(object.getTitle())
                .build());
    }

    @Override
    public Page<ConcertCategoryReadDto> findAll(Pageable pageable) {
        var result = this.repository.findAll(pageable);
        return this.repository.findAll(pageable)
                .map(readMapper::mapFrom);
    }
}
