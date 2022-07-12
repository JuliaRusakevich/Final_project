package com.gmail.juliarusakevich.event.repository;

import com.gmail.juliarusakevich.event.repository.model.EventConcert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IConcertRepository extends JpaRepository<EventConcert, UUID> {
}
