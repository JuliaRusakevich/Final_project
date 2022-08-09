package com.gmail.juliarusakevich.event.repository;

import com.gmail.juliarusakevich.event.repository.entity.Event;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEventRepository extends JpaRepository<Event, UUID> {

    Page<Event> findByStatus(EventStatus status, Pageable pageable);

    Page<Event> findByCreatedBy(String createdBy, Pageable pageable);

    Optional<Event> findByCreatedByAndUuid(String createdBy, UUID uuid);

    Optional<Event> findByStatusAndUuid(EventStatus status, UUID uuid);
}
