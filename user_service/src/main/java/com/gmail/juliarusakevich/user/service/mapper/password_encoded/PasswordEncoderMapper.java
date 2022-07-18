package com.gmail.juliarusakevich.user.service.mapper.password_encoded;

import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class PasswordEncoderMapper {

    protected final PasswordEncoder passwordEncoder;

    public PasswordEncoderMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Named("encode")
    public String encode(String value) {
        var resultPass = passwordEncoder.encode(value);
        System.out.println("Пришел такой пароль: " + value);
        System.out.println("Закодированный пароль: " + resultPass);
        return resultPass;

    }
}

