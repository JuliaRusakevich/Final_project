package com.gmail.juliarusakevich.event.mapper;

import com.gmail.juliarusakevich.event.dto.EventReadDto;
import com.gmail.juliarusakevich.event.repository.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventReadDto toDTO(Event object);
}
