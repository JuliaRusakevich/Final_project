package com.gmail.juliarusakevich.event.service.api;

import com.gmail.juliarusakevich.event.dto.EventReadDto;
import com.gmail.juliarusakevich.event.repository.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface IEvent {

    Page<EventReadDto> findAll(Pageable pageable);

    Optional<EventReadDto> find(UUID uuid);


}
