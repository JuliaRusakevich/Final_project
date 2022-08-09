package com.gmail.juliarusakevich.event.controller;


import com.gmail.juliarusakevich.event.dto.EventStatusUpdateDto;
import com.gmail.juliarusakevich.event.dto.film.FilmCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.dto.film.FilmReadDto;
import com.gmail.juliarusakevich.event.pagination.PageResponse;
import com.gmail.juliarusakevich.event.repository.entity.EventFilm;
import com.gmail.juliarusakevich.event.service.api.IFilmService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/event/film")
public class FilmController {

    private final IFilmService service;

    public FilmController(IFilmService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public EventFilm createNewEvent(@RequestBody FilmCreateAndUpdateDto dto) {
        return this.service.addEvent(dto);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<FilmReadDto> findAll(Pageable pageable) {
        var result = this.service.findAll(pageable);
        return PageResponse.of(result);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Optional<FilmReadDto> findByUuid(
            @PathVariable UUID uuid) {
        return Optional.ofNullable(this.service.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }


    @RequestMapping(value = "/{uuid}/version/{version}",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public boolean refreshDate(
            @PathVariable UUID uuid,
            @PathVariable Integer version,
            @RequestBody FilmCreateAndUpdateDto dto
    ) {
      /*
       var lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        this.service.update(uuid, lastKnowDtUpdate, dto);

       */
        this.service.update(uuid, version, dto);
        return true;
    }

    @RequestMapping(value = "/status/{uuid}/version/{version}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateStatus(@PathVariable UUID uuid,
                                          @PathVariable Integer version,
                                          @RequestBody EventStatusUpdateDto status) {
        this.service.updateStatus(uuid, version, status);
        return ResponseEntity.ok("status updated");
    }

}
