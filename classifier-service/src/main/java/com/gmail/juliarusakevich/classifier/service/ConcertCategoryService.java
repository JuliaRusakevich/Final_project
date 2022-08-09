package com.gmail.juliarusakevich.classifier.service;

import com.gmail.juliarusakevich.classifier.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryCreateDTO;
import com.gmail.juliarusakevich.classifier.dto.category.ConcertCategoryReadDTO;
import com.gmail.juliarusakevich.classifier.entity.ConcertCategory;
import com.gmail.juliarusakevich.classifier.repository.IConcertCategoryRepository;
import com.gmail.juliarusakevich.classifier.service.api.IClassifierService;
import com.gmail.juliarusakevich.classifier.service.mapper.ConcertCategoryMapper;
import com.gmail.juliarusakevich.classifier.validator.ValidationException;
import com.gmail.juliarusakevich.classifier.validator.api.IValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ConcertCategoryService
        implements IClassifierService<ConcertCategory, ConcertCategoryReadDTO, ConcertCategoryCreateDTO> {

    private final IConcertCategoryRepository repository;
    private final ConcertCategoryMapper mapper;
    private final IValidator<ConcertCategoryCreateDTO> validator;
    private final CustomErrorMessage errorMessage;

    public ConcertCategoryService(
            IConcertCategoryRepository repository,
            ConcertCategoryMapper mapper,
            IValidator<ConcertCategoryCreateDTO> validator,
            CustomErrorMessage errorMessage) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
        this.errorMessage = errorMessage;
    }

    @Override
    public ConcertCategoryReadDTO add(ConcertCategoryCreateDTO dto) {
        var validationResult = this.validator.isValid(dto);
        if (validationResult.isValid()) {
            return Optional.of(dto)
                    .map(mapper::toEntity)
                    .map(this.repository::save)
                    .map(this.mapper::toDTO)
                    .orElseThrow();
        } else {
            throw new ValidationException(validationResult.getErrors());
        }
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
                    throw new IllegalArgumentException(errorMessage.getCategoryNotFound());
                });
    }
}
