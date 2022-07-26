package com.gmail.juliarusakevich.classifier.repository;


import com.gmail.juliarusakevich.classifier.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ICountryRepository extends JpaRepository<Country, UUID> {
}
