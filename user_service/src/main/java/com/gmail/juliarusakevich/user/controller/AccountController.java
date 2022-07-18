package com.gmail.juliarusakevich.user.controller;

import com.gmail.juliarusakevich.user.service.api.IUserCreateService;
import com.gmail.juliarusakevich.user.service.dto.UserRegistration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class AccountController {

    private final IUserCreateService service;


    public AccountController(IUserCreateService service) {
        this.service = service;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserRegistration dto) {
        this.service.registerUser(dto);
        return "УРА!";
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserDetails details() {
        return this.service.getUser();

    }
}
