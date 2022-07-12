package com.gmail.juliarusakevich.event.service.mapper.film;

import com.gmail.juliarusakevich.event.service.dto.film.FilmReadDto;
import com.gmail.juliarusakevich.event.repository.model.EventFilm;
import org.springframework.stereotype.Component;


@Component
public class FilmReadMapper extends AFilmReadMapper {

    @Override
    public FilmReadDto toDto(EventFilm film) {
        if (film == null) {
            return null;
        }
        FilmReadDto dto = new FilmReadDto();
        enrichDTOWithStatusAndType(film, dto);

        dto.setUuid(film.getUuid());
        dto.setDtCreate(film.getDtCreate());
        dto.setDtUpdate(film.getDtUpdate());
        dto.setTitle(film.getTitle());
        dto.setDescription(film.getDescription());
        dto.setDtEvent(film.getDtEvent());
        dto.setDtEndOfSale(film.getDtEndOfSale());
        dto.setType(film.getType());
        dto.setStatus(dto.getStatus());
        dto.setCountry(film.getCountry());
        dto.setReleaseYear(film.getReleaseYear());
        dto.setReleaseDate(film.getReleaseDate());
        dto.setDuration(film.getDuration());

        return dto;
    }
}

