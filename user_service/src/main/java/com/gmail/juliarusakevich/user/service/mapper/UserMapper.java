package com.gmail.juliarusakevich.user.service.mapper;

import com.gmail.juliarusakevich.user.repository.model.UserCreate;
import com.gmail.juliarusakevich.user.service.dto.UserCreateUpdateDTO;
import com.gmail.juliarusakevich.user.service.dto.UserReadDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN, imports = {LocalDateTime.class})
public interface UserMapper {

    UserReadDTO toDTO(UserCreate user);

    @Mapping(source = "dtCreate", target = "dtCreate", defaultExpression = "java(LocalDateTime.now())")
    UserCreate toEntity(UserCreateUpdateDTO object);
}
