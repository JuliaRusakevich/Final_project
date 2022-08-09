package com.gmail.juliarusakevich.event.service.api;

import com.gmail.juliarusakevich.event.dto.concert.ConcertCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.dto.EventStatusUpdateDto;
import com.gmail.juliarusakevich.event.repository.entity.EventConcert;
import com.gmail.juliarusakevich.event.dto.concert.ConcertReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IConcertService {

    EventConcert addEvent(ConcertCreateAndUpdateDto object);

    Page<ConcertReadDto> findAll(Pageable pageable);

    void update(UUID uuid, Integer version, ConcertCreateAndUpdateDto dto);

    void updateStatus(UUID uuid, Integer version, EventStatusUpdateDto status);
}
