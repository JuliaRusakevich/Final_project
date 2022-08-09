package com.gmail.juliarusakevich.users.mapper;

import com.gmail.juliarusakevich.users.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.users.dto.UserReadDTO;
import com.gmail.juliarusakevich.users.entity.User;
import com.gmail.juliarusakevich.users.entity.enums.UserRole;
import com.gmail.juliarusakevich.users.entity.enums.UserStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        uses = {PasswordEncoderMapper.class, EnumMapper.class},
        imports = {LocalDateTime.class, UserRole.class, UserStatus.class})
public interface UserMapper {

    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    @Mapping(target = "role", source = "role", qualifiedByName = "role")
    @Mapping(target = "status", source = "status", qualifiedByName = "status")
    User toEntity(UserCreateUpdateDTO dto);

    UserReadDTO toDTO(User user);

    default User map(User fromObject, User toObject) {
        fromObject.setMail(toObject.getMail());
        fromObject.setNick(toObject.getNick());
        fromObject.setPassword(toObject.getPassword());
        fromObject.setRole(toObject.getRole());
        fromObject.setStatus(toObject.getStatus());
        return toObject;
    }
}
