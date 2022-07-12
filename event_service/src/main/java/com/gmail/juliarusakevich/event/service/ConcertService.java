package com.gmail.juliarusakevich.event.service;

import com.gmail.juliarusakevich.event.service.dto.concert.ConcertCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.service.dto.concert.ConcertReadDto;
import com.gmail.juliarusakevich.event.service.mapper.api.IMapper;
import com.gmail.juliarusakevich.event.service.mapper.concert.ConcertReadMapper;
import com.gmail.juliarusakevich.event.repository.model.EventConcert;
import com.gmail.juliarusakevich.event.repository.IConcertRepository;
import com.gmail.juliarusakevich.event.service.api.IConcertService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ConcertService implements IConcertService {

    private final IConcertRepository repository;
    private final IMapper<ConcertCreateAndUpdateDto, EventConcert> concertCreateMapper;
    private final ConcertReadMapper concertReadMapper;


    public ConcertService(IConcertRepository repository,
                          IMapper<ConcertCreateAndUpdateDto,
                                  EventConcert> concertCreateMapper, ConcertReadMapper concertReadMapper) {
        this.repository = repository;
        this.concertCreateMapper = concertCreateMapper;
        this.concertReadMapper = concertReadMapper;

    }


    @Override
    public EventConcert addEvent(ConcertCreateAndUpdateDto dto) {
        var concert = concertCreateMapper.map(dto);
        return this.repository.save(concert);
    }


    @Override
    public Page<ConcertReadDto> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(concertReadMapper::toDto);
    }

    @Override
    public Optional<ConcertReadDto> findByUuid(UUID uuid) {
        return this.repository.findById(uuid)
                .map(concertReadMapper::toDto);
    }

    @Override
    public void update(UUID uuid, LocalDateTime dtUpdate, ConcertCreateAndUpdateDto dto) {
        if (uuid == null) {
            throw new IllegalArgumentException("Это поле не может быть null.");
        }

        var concert = this.repository
                .findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Концерт не найден.");
                });

        if (!concert.getDtUpdate().equals(dtUpdate)) {
            throw new OptimisticLockException("Данные уже были обновлены.");
        }

        concert.setTitle(dto.getTitle());
        concert.setDescription(dto.getDescription());
        concert.setDtEvent(LocalDateTime.now());
        concert.setDtEndOfSale(LocalDateTime.now());
        concert.setType(dto.getType());
        concert.setStatus(dto.getStatus());
        concert.setCategory(dto.getCategory()); //НАПИСАТЬ ПРОВЕРКУ НА СУЩЕСТВОВАНИЯ ТАКОЙ КАТЕГОРИИ

        this.repository.save(concert);
    }
}
