package com.gmail.juliarusakevich.event.repository.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

@MappedSuperclass//чтобы остальные сущности унаследовали все поля
@EntityListeners(AuditingEntityListener.class)//слушает изменения, листнер спринга
//Все сущности, которые будут экстендится, автоматически будут аудироваться
public abstract class AuditingEntity<T> {

    @CreatedDate//чтобы листнер понял, что от него хотят
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
    //String не имеет никакого смысла, надо помочь спрингу!
    // в AuditConfig надо предоставить провайдер  AuditorAware<String>
    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

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
