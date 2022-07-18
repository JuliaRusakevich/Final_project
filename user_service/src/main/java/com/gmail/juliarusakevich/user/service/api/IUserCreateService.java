package com.gmail.juliarusakevich.user.service.api;


import com.gmail.juliarusakevich.user.repository.model.User;
import com.gmail.juliarusakevich.user.service.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.user.service.dto.UserReadDTO;
import com.gmail.juliarusakevich.user.service.dto.UserRegistration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IUserCreateService {

    User addUser(UserCreateUpdateDTO dto);

    Page<UserReadDTO> findAll(Pageable pageable);

    Optional<UserReadDTO> findById(UUID uuid);

    User updateUserInfo(UUID uuid, LocalDateTime dtUpdate, UserCreateUpdateDTO dto);

    User registerUser(UserRegistration dto);

    UserDetails getUser();

}
