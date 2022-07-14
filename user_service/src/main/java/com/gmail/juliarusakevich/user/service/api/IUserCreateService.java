package com.gmail.juliarusakevich.user.service.api;


import com.gmail.juliarusakevich.user.repository.model.UserCreate;
import com.gmail.juliarusakevich.user.service.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.user.service.dto.UserReadDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IUserCreateService {

    UserCreate addUser(UserCreateUpdateDTO dto);

    Page<UserReadDTO> findAll(Pageable pageable);

    Optional<UserReadDTO> findById(UUID uuid);

    UserCreate updateUserInfo(UUID uuid, LocalDateTime dtUpdate, UserCreateUpdateDTO dto);


   // Boolean checkNick(String nick);

    //Boolean checkMail(String mail);
}
