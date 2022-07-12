package com.gmail.juliarusakevich.event.service.api;

import com.gmail.juliarusakevich.event.service.dto.concert.ConcertReadDto;
import com.gmail.juliarusakevich.event.service.dto.concert.ConcertCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.repository.model.EventConcert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IConcertService {

    EventConcert addEvent(ConcertCreateAndUpdateDto object);

    Page<ConcertReadDto> findAll(Pageable pageable);

    Optional<ConcertReadDto> findByUuid(UUID uuid);

    void update(UUID uuid, LocalDateTime dtUpdate, ConcertCreateAndUpdateDto dto);
}
