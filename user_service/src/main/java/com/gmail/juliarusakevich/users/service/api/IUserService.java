package com.gmail.juliarusakevich.users.service.api;

import com.gmail.juliarusakevich.users.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.users.dto.UserReadDTO;
import com.gmail.juliarusakevich.users.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    void register(User dto);

    User findByMail(String mail);

    User add(User object);

    Page<UserReadDTO> findAll(Pageable pageable);

    Optional<UserReadDTO> findById(UUID uuid);

    void updateInfo(UUID uuid, LocalDateTime dtUpdate, User object);


}
