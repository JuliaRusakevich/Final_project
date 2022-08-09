package com.gmail.juliarusakevich.event.mapper.film;

import com.gmail.juliarusakevich.event.dto.concert.ConcertCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.dto.film.FilmCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.dto.film.FilmReadDto;
import com.gmail.juliarusakevich.event.repository.entity.EventConcert;
import com.gmail.juliarusakevich.event.repository.entity.EventFilm;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventStatus;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Mapper(componentModel = "spring",
        imports = {EventType.class, EventStatus.class})
public interface FilmMapper {

    @Mapping(source = "type", target = "type", defaultExpression = "java( dto.getType())")
    @Mapping(source = "status", target = "status", defaultExpression = "java( dto.getStatus())")
    EventFilm toEntity(FilmCreateAndUpdateDto dto);

    FilmReadDto toDTO(EventFilm object);

}


