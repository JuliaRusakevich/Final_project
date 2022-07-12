package com.gmail.juliarusakevich.event.service.mapper.concert;

import com.gmail.juliarusakevich.event.service.dto.concert.ConcertCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.service.mapper.api.IMapper;
import com.gmail.juliarusakevich.event.repository.model.EventConcert;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConcertCreateMapper implements IMapper<ConcertCreateAndUpdateDto, EventConcert> {

    @Override
    public EventConcert map(ConcertCreateAndUpdateDto dto) {
        return EventConcert.builder()
                .dtCreate(LocalDateTime.now())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dtEvent(LocalDateTime.now()) //НОРМАЛЬНУЮ ДАТУ И ВРЕМЯ ПЕРЕДАТЬ!!!
                .dtEndOfSale(LocalDateTime.now()) //НОРМАЛЬНУЮ ДАТУ И ВРЕМЯ ПЕРЕДАТЬ!!!
                .eventType(dto.getType())
                .eventStatus(dto.getStatus())
                .category(dto.getCategory())
                .build();
    }
}

