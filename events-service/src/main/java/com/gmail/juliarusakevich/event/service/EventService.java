package com.gmail.juliarusakevich.event.service;

import com.gmail.juliarusakevich.event.dto.EventReadDto;
import com.gmail.juliarusakevich.event.mapper.EventMapper;
import com.gmail.juliarusakevich.event.repository.IEventRepository;;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventStatus;
import com.gmail.juliarusakevich.event.service.api.IEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class EventService implements IEvent {

    private final IEventRepository repository;
    private final UserHolder userHolder;
    private final EventMapper mapper;

    public EventService(IEventRepository repository,
                        UserHolder userHolder,
                        EventMapper mapper) {
        this.repository = repository;
        this.userHolder = userHolder;
        this.mapper = mapper;
    }

    //ищем всех по ролям
    @Override
    public Page<EventReadDto> findAll(Pageable pageable) {
        if (this.userHolder.isAdmin()) {

            return this.repository.findAll(pageable)
                    .map(mapper::toDTO);
        }
        if (this.userHolder.isUser()) {
            return this.repository.findByCreatedBy(this.userHolder.getUsername(), pageable)
                    .map(mapper::toDTO);
        }
        return this.repository.findByStatus(EventStatus.PUBLISHED, pageable)
                .map(mapper::toDTO);
    }

    @Override
    public Optional<EventReadDto> find(UUID uuid) {
        if (this.userHolder.isAdmin()) {
            return this.repository.findById(uuid)
                    .map(mapper::toDTO);
        }
        if (this.userHolder.isUser()) {
            return this.repository.findByCreatedByAndUuid(this.userHolder.getUsername(), uuid)
                    .map(mapper::toDTO);
        }
        return this.repository.findByStatusAndUuid(EventStatus.PUBLISHED, uuid)
                .map(mapper::toDTO);
    }


}


