package com.gmail.juliarusakevich.event.controller;

import com.gmail.juliarusakevich.event.service.dto.film.FilmCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.service.dto.film.FilmReadDto;
import com.gmail.juliarusakevich.event.repository.model.EventFilm;
import com.gmail.juliarusakevich.event.service.pagination.PageResponse;
import com.gmail.juliarusakevich.event.service.api.IFilmService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
public class FilmController {

    private final IFilmService service;

    public FilmController(IFilmService service) {
        this.service = service;
    }

    /*
{
  "title": "title film",
  "description": "description film",
  "dt_event": null,
  "dt_end_of_sale": null,
  "type": "FILMS",
  "status": "DRAFT",
  "country": "8e81e35c-3193-4e8e-9104-6e7fdf7fdb61", ИЗ СПРАВОЧНИКА ДОСТАТЬ
  "release_year": 2022,
  "release_date": "30 июня 2022",
  "duration": 100
}
     */

    @RequestMapping(value = "/api/v1/afisha/event/film", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public EventFilm createNewEvent(@RequestBody FilmCreateAndUpdateDto object) {
        return this.service.addEvent(object);
    }

    @RequestMapping(value = "/api/v1/afisha/event/film", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<FilmReadDto> findAll(Pageable pageable) {
        var result = this.service.findAll(pageable);
        System.out.println(result);
        return PageResponse.of(result);
    }

    @RequestMapping(value = "/api/v1/afisha/event/film/{uuid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Optional<FilmReadDto> findByUuid(
            @PathVariable UUID uuid) {
        return Optional.ofNullable(this.service.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }


    @RequestMapping(value = "/api/v1/afisha/event/film/{uuid}/dt_update/{dtUpdate}",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public boolean refreshDate(
            @PathVariable UUID uuid,
            @PathVariable Long dtUpdate,
            @RequestBody FilmCreateAndUpdateDto dto
    ) {
        var lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        this.service.update(uuid, lastKnowDtUpdate, dto);
        return true;
    }

}
