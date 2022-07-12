package com.gmail.juliarusakevich.event.service.api;

import com.gmail.juliarusakevich.event.service.dto.film.FilmReadDto;
import com.gmail.juliarusakevich.event.service.dto.film.FilmCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.repository.model.EventFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IFilmService {

    EventFilm addEvent(FilmCreateAndUpdateDto object);

    Page<FilmReadDto> findAll(Pageable pageable);

    Optional<FilmReadDto> findByUuid(UUID uuid);

    void update(UUID uuid, LocalDateTime dtUpdate, FilmCreateAndUpdateDto dto);

}
