package com.gmail.juliarusakevich.users.controller;

import com.gmail.juliarusakevich.users.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.users.dto.UserLogin;
import com.gmail.juliarusakevich.users.dto.UserRegistration;
import com.gmail.juliarusakevich.users.mapper.UserMapper;
import com.gmail.juliarusakevich.users.service.UserHolder;
import com.gmail.juliarusakevich.users.service.UsersService;
import com.gmail.juliarusakevich.users.controller.utils.JwtTokenUtil;
import com.gmail.juliarusakevich.users.validator.api.IValidator;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class AccountController {

    private final UsersService service;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;
    private final UserHolder userHolder;
    private final IValidator<UserRegistration> validator;

    public AccountController(UsersService service,
                             PasswordEncoder encoder,
                             UserMapper mapper,
                             UserHolder userHolder,
                             IValidator<UserRegistration> validator) {
        this.service = service;
        this.encoder = encoder;
        this.mapper = mapper;
        this.userHolder = userHolder;
        this.validator = validator;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserRegistration dto) {
        var user = mapper.toEntityRegister(dto);
        this.validator.isValid(dto);
        this.service.register(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String authentication(@RequestBody UserLogin dto) {
        var user = this.service.findByMail(dto.getMail());
      var details =  this.service.loadUserByUsername(dto.getMail());
        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect Password");
        }
        /*
        После того, как убедились, что пользователь действительно тот, генерируем  jwt token
        package by.itacademy.user.controller.utils;

        При логине вернули строку с jwt token ->

        Получили токен и идем в постман, передали json -> вернулся токет
        {
        "login": "user",
        "password": "123"
         }

         -> jwt.io

         после получения токена идем в package by.itacademy.user.controller.TestController;
        */
        return JwtTokenUtil.generateAccessToken(details);
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserDetails details() {
        return this.userHolder.getUser();

    }
}
