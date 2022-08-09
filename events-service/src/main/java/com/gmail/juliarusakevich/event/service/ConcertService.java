package com.gmail.juliarusakevich.event.service;

import com.gmail.juliarusakevich.event.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.event.dto.EventStatusUpdateDto;
import com.gmail.juliarusakevich.event.mapper.concert.ConcertMapper;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventStatus;
import com.gmail.juliarusakevich.event.service.api.IConcertService;
import com.gmail.juliarusakevich.event.dto.concert.ConcertCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.dto.concert.ConcertReadDto;

import com.gmail.juliarusakevich.event.repository.IConcertRepository;

import com.gmail.juliarusakevich.event.repository.entity.EventConcert;
import com.gmail.juliarusakevich.event.validator.ValidationException;
import com.gmail.juliarusakevich.event.validator.api.IValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.util.UUID;

@Transactional
@Service
public class ConcertService implements IConcertService {

    private final IConcertRepository repository;
    private final ConcertMapper concertMapper;
    private final UserHolder userHolder;
    private final CustomErrorMessage errorMessage;
    private final IValidator<ConcertCreateAndUpdateDto> validator;

    public ConcertService(IConcertRepository repository,
                          ConcertMapper concertMapper,
                          UserHolder userHolder,
                          CustomErrorMessage errorMessage,
                          IValidator<ConcertCreateAndUpdateDto> validator) {
        this.repository = repository;
        this.concertMapper = concertMapper;
        this.userHolder = userHolder;
        this.errorMessage = errorMessage;
        this.validator = validator;
    }

    @Override
    public EventConcert addEvent(ConcertCreateAndUpdateDto dto) {
        var validationResult = this.validator.isValid(dto);

        if (!this.userHolder.isEnableAccount()) {
            throw new IllegalArgumentException(errorMessage.getAccountNotActivated());
        }

        if (validationResult.isValid()) {
            var concert = concertMapper.toEntity(dto);
            return this.repository.save(concert);
        } else {
            throw new ValidationException(validationResult.getErrors());

        }
    }

    @Override
    public Page<ConcertReadDto> findAll(Pageable pageable) {

        if (this.userHolder.isAdmin()) {
            return this.repository.findAll(pageable)
                    .map(concertMapper::toDTO);
        }
        if (this.userHolder.isUser()) {
            var username = this.userHolder.getUsername();
            return this.repository.findByCreatedBy(username, pageable)
                    .map(concertMapper::toDTO);
        } else {
            return this.repository.findByStatus(EventStatus.PUBLISHED, pageable)
                    .map(concertMapper::toDTO);
        }
    }


    @Override
    @Transactional
    public void update(UUID uuid, Integer version, ConcertCreateAndUpdateDto dto) {
        EventConcert fromConcert = getEventConcertFromDB(uuid, version);
        map(dto, fromConcert);
        this.repository.saveAndFlush(fromConcert);
    }

    @Override
    @Transactional
    public void updateStatus(UUID uuid, Integer version, EventStatusUpdateDto status) {
        var concert = getEventConcertFromDB(uuid, version);
        concert.setStatus(status.getStatus());
        this.repository.saveAndFlush(concert);

    }

    private EventConcert getEventConcertFromDB(UUID uuid, Integer version) {
        var fromConcert = this.repository.findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(
                            this.errorMessage.getConcertNotFound()
                    );
                });
        if (!fromConcert.getVersion().equals(version)) {
            throw new OptimisticLockException(
                    this.errorMessage.getUpdatedInfo()
            );
        }
        return fromConcert;
    }

    private void map(ConcertCreateAndUpdateDto dto, EventConcert fromConcert) {
        fromConcert.setTitle(dto.getTitle());
        fromConcert.setDescription(dto.getDescription());
        fromConcert.setDtEvent(dto.getDtEvent());
        fromConcert.setDtEndOfSale(dto.getDtEndOfSale());
        fromConcert.setType(dto.getType());
        fromConcert.setStatus(dto.getStatus());
        fromConcert.setCategory(dto.getCategory());
    }
}
