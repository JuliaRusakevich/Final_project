package com.gmail.juliarusakevich.event.mapper.concert;

import com.gmail.juliarusakevich.event.dto.concert.ConcertCreateAndUpdateDto;
import com.gmail.juliarusakevich.event.dto.concert.ConcertReadDto;
import com.gmail.juliarusakevich.event.repository.entity.EventConcert;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventStatus;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

//imports = {LocalDateTime.class, UserRole.class, UserStatus.class}
@Mapper(componentModel = "spring",
        imports = {LocalDateTime.class, EventType.class, EventStatus.class})
public interface ConcertMapper {

   // @Mapping(source = "dtCreate", target = "dtCreate", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(source = "type", target = "type", defaultExpression = "java( dto.getType())")
    @Mapping(source = "status", target = "status", defaultExpression = "java( dto.getStatus())")
    EventConcert toEntity(ConcertCreateAndUpdateDto dto);

    ConcertReadDto toDTO(EventConcert object);
}
