package com.gmail.juliarusakevich.user.repository.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass//чтобы остальные сущности унаследовали все поля
@EntityListeners(AuditingEntityListener.class)//слушает изменения, листнер спринга
//Все сущности, которые будут экстендится, автоматически будут аудироваться
public abstract class AuditingEntity<T> {

    @CreatedDate//чтобы листнер понял, что от него хотят
    private Instant createdAt;

    @LastModifiedDate
    private Instant modifiedAt;

    /*
    String не имеет никакого смысла, надо помочь спрингу!
    в AuditConfig надо предоставить провайдер  AuditorAware<String>
     */
    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
