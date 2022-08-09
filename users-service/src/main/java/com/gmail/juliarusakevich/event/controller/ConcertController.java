package com.gmail.juliarusakevich.event.controller;

import com.gmail.juliarusakevich.event.dto.EventStatusUpdateDto;
import com.gmail.juliarusakevich.event.service.api.IConcertService;
import com.gmail.juliarusakevich.event.dto.concert.ConcertCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.dto.concert.ConcertReadDto;
import com.gmail.juliarusakevich.event.pagination.PageResponse;
import com.gmail.juliarusakevich.event.repository.entity.EventConcert;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/event/concert")
public class ConcertController {

    private final IConcertService service;


    public ConcertController(IConcertService service) {
        this.service = service;

    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public EventConcert addConcert(@RequestBody ConcertCreateAndUpdateDto dto) {
        return this.service.addEvent(dto);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ConcertReadDto> findAll(Pageable pageable) {
        var result = this.service.findAll(pageable);
        return PageResponse.of(result);
    }


    @RequestMapping(value = "/{uuid}/version/{version}",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public boolean refreshDate(
            @PathVariable UUID uuid,
            @PathVariable Integer version,
            @RequestBody ConcertCreateAndUpdateDto dto
    ) {
    /*
     var lastKnowDtUpdate =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        this.service.update(uuid, version, dto);
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
