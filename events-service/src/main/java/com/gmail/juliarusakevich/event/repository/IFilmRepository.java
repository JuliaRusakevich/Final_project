package com.gmail.juliarusakevich.event.repository;

import com.gmail.juliarusakevich.event.repository.entity.EventFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFilmRepository extends JpaRepository<EventFilm, UUID> {
}
