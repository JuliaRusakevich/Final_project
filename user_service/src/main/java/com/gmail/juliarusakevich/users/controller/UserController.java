package com.gmail.juliarusakevich.users.controller;

import com.gmail.juliarusakevich.users.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.users.dto.UserReadDTO;
import com.gmail.juliarusakevich.users.dto.UserStatusDto;
import com.gmail.juliarusakevich.users.entity.enums.UserStatus;
import com.gmail.juliarusakevich.users.pagination.PageResponse;
import com.gmail.juliarusakevich.users.service.api.IUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDTO addUser(@RequestBody UserCreateUpdateDTO dto) {
        return this.service.add(dto);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<UserReadDTO> findAll(Pageable pageable) {
        var users = this.service.findAll(pageable);
        return PageResponse.of(users);
    }

    /*
    При обращении по несуществующему url - NOT_FOUND
    uuid - часть url, если не нашли по id HttpStatus.NOT_FOUND
     */
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Optional<UserReadDTO> findByUuid(@PathVariable UUID uuid) {
        return Optional.ofNullable(this.service.findById(uuid))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @RequestMapping(value = "/{uuid}/version/{version}",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Optional<UserReadDTO> update(
            @PathVariable UUID uuid,
            @PathVariable Integer version,
            @RequestBody UserCreateUpdateDTO dto) {
        return Optional.ofNullable(this.service.update(uuid, version, dto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/status/{uuid}/version/{version}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateStatus(@PathVariable UUID uuid,
                                          @PathVariable Integer version,
                                          @RequestBody UserStatusDto  status) {
        this.service.updateStatus(uuid, version, status);
        return ResponseEntity.ok("status updated");
    }

}
