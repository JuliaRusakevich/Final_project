package com.gmail.juliarusakevich.users.controller;

import com.gmail.juliarusakevich.users.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.users.dto.UserReadDTO;
import com.gmail.juliarusakevich.users.entity.User;
import com.gmail.juliarusakevich.users.mapper.UserMapper;
import com.gmail.juliarusakevich.users.pagination.PageResponse;
import com.gmail.juliarusakevich.users.service.api.IUserService;
import com.gmail.juliarusakevich.users.validator.UserCreateUpdateValidator;
import com.gmail.juliarusakevich.users.validator.api.IValidator;
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

    private final IUserService service;
    private final UserMapper mapper;
    private final IValidator<UserCreateUpdateDTO> validator;

    public UserController(IUserService service,
                          UserMapper mapper,
                          IValidator<UserCreateUpdateDTO> validator) {
        this.service = service;
        this.mapper = mapper;
        this.validator = validator;
    }


    @RequestMapping(value = "/api/v1/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserCreateUpdateDTO dto) {
        var user = this.mapper.toEntity(dto);
        return this.service.add(user);
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

    @RequestMapping(value = "/api/v1/users/{uuid}/dt_update/{dtUpdate}",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public boolean refreshDate(
            @PathVariable UUID uuid,
            @PathVariable Long dtUpdate,
            @RequestBody UserCreateUpdateDTO dto
    ) {
        var lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        validator.isValid(dto);
        var user = this.mapper.toEntity(dto);
        this.service.updateInfo(uuid, lastKnowDtUpdate, user);
        return true;
    }
}
