package com.gmail.juliarusakevich.users.service.api;

import com.gmail.juliarusakevich.users.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.users.dto.UserReadDTO;
import com.gmail.juliarusakevich.users.dto.UserStatusDto;
import com.gmail.juliarusakevich.users.entity.User;
import com.gmail.juliarusakevich.users.entity.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    User findByMail(String mail);

    UserReadDTO add(UserCreateUpdateDTO dto);

    Page<UserReadDTO> findAll(Pageable pageable);

    Optional<UserReadDTO> findById(UUID uuid);

    Optional<UserReadDTO> update(UUID uuid, Integer version, UserCreateUpdateDTO dto);

    void updateStatus(UUID uuid, Integer version, UserStatusDto status);


}
