package com.gmail.juliarusakevich.user.service.mapper;

import com.gmail.juliarusakevich.user.repository.model.User;
import com.gmail.juliarusakevich.user.repository.model.enums.UserRole;
import com.gmail.juliarusakevich.user.repository.model.enums.UserStatus;
import com.gmail.juliarusakevich.user.service.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.user.service.dto.UserReadDTO;
import com.gmail.juliarusakevich.user.service.dto.UserRegistration;
import com.gmail.juliarusakevich.user.service.mapper.password_encoded.PasswordEncoderMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        uses = PasswordEncoderMapper.class,
        imports = {LocalDateTime.class, UserRole.class, UserStatus.class})
public interface UserMapper {

    UserReadDTO toDTO(User user);

    @Mapping(source = "dtCreate", target = "dtCreate", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    User toEntity(UserCreateUpdateDTO object);

    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    @Mapping(target = "role", source = "role", defaultExpression = "java(UserRole.USER)")
    @Mapping(target = "status", source = "status", defaultExpression = "java(UserStatus.WAITING_ACTIVATION)")
    @Mapping(source = "dtCreate", target = "dtCreate", defaultExpression = "java(LocalDateTime.now())")
    User toEntityRegister(UserRegistration dto);


}
