package com.gmail.juliarusakevich.classifier.service;

import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryReadDTO;
import com.gmail.juliarusakevich.classifier.entity.ConcertCategory;
import com.gmail.juliarusakevich.classifier.repository.IConcertCategoryRepository;
import com.gmail.juliarusakevich.classifier.service.api.IClassifierService;
import com.gmail.juliarusakevich.classifier.service.mapper.ConcertCategoryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConcertCategoryService implements IClassifierService<ConcertCategory, ConcertCategoryReadDTO> {

    private final IConcertCategoryRepository repository;
    private final ConcertCategoryMapper mapper;

    public ConcertCategoryService(
            IConcertCategoryRepository repository,
            ConcertCategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public ConcertCategory add(ConcertCategory object) {
        return this.repository.save(object);
    }

    @Override
    public Page<ConcertCategoryReadDTO> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(mapper::toDTO);
    }

    @Override
    public ConcertCategoryReadDTO findByUUID(UUID uuid) {
        return this.repository.findById(uuid)
                .map(mapper::toDTO)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Country not found");
                });
    }
}
