package com.gmail.juliarusakevich.event.service.mapper.concert;

import com.gmail.juliarusakevich.event.service.dto.concert.ConcertReadDto;
import com.gmail.juliarusakevich.event.repository.model.Event;
import com.gmail.juliarusakevich.event.repository.model.EventConcert;
import com.gmail.juliarusakevich.event.repository.model.EventFilm;
import com.gmail.juliarusakevich.event.repository.model.enums.EventStatus;
import com.gmail.juliarusakevich.event.repository.model.enums.EventType;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class AConcertReadMapper {

    @BeforeMapping
    protected void enrichDTOWithStatusAndType(
            Event event,
            @MappingTarget ConcertReadDto concertDto) {
        if (event instanceof EventFilm) {
            concertDto.setStatus(EventStatus.DRAFT);
            concertDto.setType(EventType.FILMS);
        }
    }

    public abstract ConcertReadDto toDto(EventConcert concert);
}
