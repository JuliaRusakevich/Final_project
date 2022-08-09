package com.gmail.juliarusakevich.event.controller;

import com.gmail.juliarusakevich.event.dto.EventReadDto;
import com.gmail.juliarusakevich.event.pagination.PageResponse;
import com.gmail.juliarusakevich.event.service.api.IEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/event")
public class EventController {

    private final IEvent service;

    public EventController(IEvent service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public PageResponse<EventReadDto> findAll(Pageable pageable) {
        var events = this.service.findAll(pageable);
        return PageResponse.of(events);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    private EventReadDto findEventByUuid(@PathVariable UUID uuid) {
        return this.service.find(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
