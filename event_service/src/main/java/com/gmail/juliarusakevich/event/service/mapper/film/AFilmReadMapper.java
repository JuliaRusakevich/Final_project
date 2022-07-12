package com.gmail.juliarusakevich.event.service.mapper.film;

import com.gmail.juliarusakevich.event.service.dto.film.FilmReadDto;
import com.gmail.juliarusakevich.event.repository.model.Event;
import com.gmail.juliarusakevich.event.repository.model.EventFilm;
import com.gmail.juliarusakevich.event.repository.model.enums.EventStatus;
import com.gmail.juliarusakevich.event.repository.model.enums.EventType;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class AFilmReadMapper {

    @BeforeMapping
    protected void enrichDTOWithStatusAndType(
            Event event,
            @MappingTarget FilmReadDto filmDto) {
        if (event instanceof EventFilm) {
            filmDto.setStatus(EventStatus.DRAFT);
            filmDto.setType(EventType.FILMS);
        }
    }

    public abstract FilmReadDto toDto(EventFilm films);
}
