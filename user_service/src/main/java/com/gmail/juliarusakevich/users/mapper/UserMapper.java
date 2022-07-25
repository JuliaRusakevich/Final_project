package com.gmail.juliarusakevich.users.mapper;

import com.gmail.juliarusakevich.users.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.users.dto.UserReadDTO;
import com.gmail.juliarusakevich.users.dto.UserRegistration;
import com.gmail.juliarusakevich.users.entity.User;
import com.gmail.juliarusakevich.users.entity.enums.UserRole;
import com.gmail.juliarusakevich.users.entity.enums.UserStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        uses = PasswordEncoderMapper.class,
        imports = {LocalDateTime.class, UserRole.class, UserStatus.class})
public interface UserMapper {

    @Mapping(target = "role", source = "role", defaultExpression = "java(Set.of(UserRole.ROLE_USER))")
    @Mapping(target = "status", source = "status", defaultExpression = "java(UserStatus.WAITING_ACTIVATION)")
    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    @Mapping(target = "createdBy", source = "createdBy", defaultExpression = "java( dto.getMail())")
    User toEntityRegister(UserRegistration dto);

    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    User toEntity(UserCreateUpdateDTO dto);

   // @Mapping(target = "mail", source = "mail", defaultExpression = "java( user.getMail())")
    UserReadDTO toDTO(User user);
}
