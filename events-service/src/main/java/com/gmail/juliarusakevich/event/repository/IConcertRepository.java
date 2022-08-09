package com.gmail.juliarusakevich.event.repository;

import com.gmail.juliarusakevich.event.repository.entity.EventConcert;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IConcertRepository extends JpaRepository<EventConcert, UUID> {

    Page<EventConcert> findByStatus(EventStatus status, Pageable pageable);

    Page<EventConcert> findByCreatedBy(String createdBy, Pageable pageable);

    Optional<EventConcert> findByCreatedByAndUuid(String createdBy, UUID uuid);
}
