package com.gmail.juliarusakevich.classifier.repository;

import com.gmail.juliarusakevich.classifier.model.ConcertCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IConcertCategoryRepository extends JpaRepository<ConcertCategory, UUID> {
}
