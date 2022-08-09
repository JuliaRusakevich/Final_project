package com.gmail.juliarusakevich.users.entity;

import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass//чтобы остальные сущности унаследовали все
@EntityListeners(AuditingEntityListener.class)//слушает изменения, листнер спринга
//Все сущности, которые будут экстендится, автоматически будут аудироваться
public abstract class AuditingEntity<T> {

    @CreatedDate//чтобы листнер понял, что от него хотят
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
