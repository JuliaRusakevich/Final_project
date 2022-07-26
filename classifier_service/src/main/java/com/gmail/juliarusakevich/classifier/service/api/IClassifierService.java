package com.gmail.juliarusakevich.classifier.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IClassifierService<E, R> {

    E add(E object);

    Page<R> findAll(Pageable pageable);

    R findByUUID(UUID uuid);
}
