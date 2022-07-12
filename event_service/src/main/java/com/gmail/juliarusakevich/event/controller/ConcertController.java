package com.gmail.juliarusakevich.event.controller;

import com.gmail.juliarusakevich.event.service.dto.concert.ConcertCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.service.dto.concert.ConcertReadDto;
import com.gmail.juliarusakevich.event.repository.model.EventConcert;
import com.gmail.juliarusakevich.event.service.pagination.PageResponse;
import com.gmail.juliarusakevich.event.service.api.IConcertService;
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
public class ConcertController {

    private final IConcertService service;

    public ConcertController(IConcertService service) {
        this.service = service;
    }

        /*
{
  "title": "title CONCERT",
  "description": "description CONCERT",
  "dt_event": null,
  "dt_end_of_sale": null,
  "type": "CONCERTS",
  "status": "DRAFT",
  "category": "c279ed31-f777-4049-a523-a3ba7d7e1537", ИЗ СПРАВОЧНИКА ДОСТАТЬ


}
     */

    @RequestMapping(value = "/api/v1/afisha/event/concert", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public EventConcert addConcert(@RequestBody ConcertCreateAndUpdateDto dto) {
        return this.service.addEvent(dto);
    }

    @RequestMapping(value = "/api/v1/afisha/event/concert", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ConcertReadDto> findAll(Pageable pageable) {
        var result = this.service.findAll(pageable);
        return PageResponse.of(result);
    }

    @RequestMapping(value = "/api/v1/afisha/event/concert/{uuid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Optional<ConcertReadDto> findById(@PathVariable UUID uuid) {
        return Optional.ofNullable(this.service.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @RequestMapping(value = "/api/v1/afisha/event/concert/{uuid}/dt_update/{dtUpdate}",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public boolean refreshDate(
            @PathVariable UUID uuid,
            @PathVariable Long dtUpdate,
            @RequestBody ConcertCreateAndUpdateDto dto
    ) {
        var lastKnowDtUpdate =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        this.service.update(uuid, lastKnowDtUpdate, dto);
        return true;
    }
}
