package com.gmail.juliarusakevich.event.service.mapper.concert;

import com.gmail.juliarusakevich.event.service.dto.concert.ConcertReadDto;
import com.gmail.juliarusakevich.event.repository.model.EventConcert;
import org.springframework.stereotype.Component;

@Component
public class ConcertReadMapper extends AConcertReadMapper {
    @Override
    public ConcertReadDto toDto(EventConcert concert) {
        if (concert == null) {
            return null;
        }

        ConcertReadDto dto = new ConcertReadDto();

        dto.setUuid(concert.getUuid());
        dto.setTitle(concert.getTitle());
        dto.setDescription(concert.getDescription());
        dto.setDtCreate(concert.getDtCreate());
        dto.setDtUpdate(concert.getDtUpdate());
        dto.setType(concert.getType());
        dto.setStatus(concert.getStatus());
        dto.setCategory(concert.getCategory());

        return dto;
    }
}

