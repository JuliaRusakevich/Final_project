package com.gmail.juliarusakevich.users.controller;

import com.gmail.juliarusakevich.users.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.users.controller.utils.JwtTokenUtil;
import com.gmail.juliarusakevich.users.dto.CustomUserDetails;
import com.gmail.juliarusakevich.users.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.users.dto.UserLogin;
import com.gmail.juliarusakevich.users.dto.UserReadDTO;
import com.gmail.juliarusakevich.users.service.UserHolder;
import com.gmail.juliarusakevich.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class AccountController {

    private final UsersService service;
    private final PasswordEncoder encoder;
    private final UserHolder userHolder;
    private final CustomErrorMessage errorMessage;


    public AccountController(UsersService service,
                             PasswordEncoder encoder,
                             UserHolder userHolder,
                             CustomErrorMessage errorMessage) {
        this.service = service;
        this.encoder = encoder;
        this.userHolder = userHolder;
        this.errorMessage = errorMessage;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserCreateUpdateDTO dto) {
        this.service.add(dto);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String authentication(@RequestBody UserLogin dto) {
        var user = this.service.findByMail(dto.getMail());
        var details = this.service.loadUserByUsername(dto.getMail());
        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException(
                    this.errorMessage.getIncorrectPassword()
            );
        }
        /*
        После того, как убедились, что пользователь действительно тот, генерируем  jwt token
        package ...utils;

        При логине вернули строку с jwt token ->

        Получили токен и идем в postman, передали json -> вернулся token
        {
        "login": "user",
        "password": "123"
         }

         -> jwt.io

         после получения токена идем в package ...controller.TestController;
        */
        return JwtTokenUtil.generateAccessToken(details);
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CustomUserDetails details() {
        return (CustomUserDetails)this.userHolder.getUser();
    }
}
