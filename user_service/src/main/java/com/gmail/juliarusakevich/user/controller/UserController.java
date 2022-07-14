package com.gmail.juliarusakevich.user.controller;

import com.gmail.juliarusakevich.user.repository.model.UserCreate;
import com.gmail.juliarusakevich.user.service.api.IUserCreateService;
import com.gmail.juliarusakevich.user.service.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.user.service.dto.UserReadDTO;
import com.gmail.juliarusakevich.user.service.pagination.PageResponse;
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
public class UserController {

    private final IUserCreateService service;


    public UserController(IUserCreateService service) {
        this.service = service;
    }

    @RequestMapping(value = "/api/v1/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreate addUser(@RequestBody UserCreateUpdateDTO dto) {
        return this.service.addUser(dto);
    }

    @RequestMapping(value = "/api/v1/users", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<UserReadDTO> findAll(Pageable pageable) {
        var users = this.service.findAll(pageable);
        return PageResponse.of(users);
    }


    @RequestMapping(value = "/api/v1/users/{uuid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Optional<UserReadDTO> findByUuid(@PathVariable UUID uuid) {
        return Optional.ofNullable(this.service.findById(uuid))
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/api/v1/users/{uuid}/dt_update/{dtUpdate}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public boolean refreshDate(
            @PathVariable UUID uuid,
            @PathVariable Long dtUpdate,
            @RequestBody UserCreateUpdateDTO dto
    ) {
        var lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        this.service.updateUserInfo(uuid, lastKnowDtUpdate, dto);
        return true;
    }

}
