package com.gmail.juliarusakevich.event.service.api;

import com.gmail.juliarusakevich.event.dto.EventStatusUpdateDto;
import com.gmail.juliarusakevich.event.dto.film.FilmReadDto;
import com.gmail.juliarusakevich.event.dto.film.FilmCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.repository.entity.EventFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IFilmService {

    EventFilm addEvent(FilmCreateAndUpdateDto object);

    Page<FilmReadDto> findAll(Pageable pageable);

    Optional<FilmReadDto> findByUuid(UUID uuid);

    void update(UUID uuid, Integer version, FilmCreateAndUpdateDto dto);

    void updateStatus(UUID uuid, Integer version, EventStatusUpdateDto status);

}
