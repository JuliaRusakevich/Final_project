package com.gmail.juliarusakevich.classifier.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;
/*
C - dto for create/update
R - dto for read
E - entity
 */
public interface IClassifierService<E, R, C> {

    R add(C dto);

    Page<R> findAll(Pageable pageable);

    R findByUUID(UUID uuid);
}
