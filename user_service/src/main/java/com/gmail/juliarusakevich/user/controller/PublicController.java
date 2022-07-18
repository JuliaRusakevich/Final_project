package com.gmail.juliarusakevich.user.controller;

import com.gmail.juliarusakevich.user.controller.utils.JwtTokenUtil;
import com.gmail.juliarusakevich.user.service.dto.UserLogin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicController {

   // private final UserService service;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsManager userManager;

    public PublicController( PasswordEncoder passwordEncoder, UserDetailsManager userManager) {
        this.passwordEncoder = passwordEncoder;
        this.userManager = userManager;
    }


    @RequestMapping(value = "/api/v1/users/login", method = RequestMethod.GET)
    public String login(@RequestBody UserLogin loginDto) {

        UserDetails details = userManager.loadUserByUsername(loginDto.getUsername());

        if (!passwordEncoder.matches(loginDto.getPassword(), details.getPassword()))
            throw new IllegalArgumentException("Wrong password.");

        return JwtTokenUtil.generateAccessToken(details);

    }


}
