package com.gmail.juliarusakevich.event.dto.concert;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gmail.juliarusakevich.event.controller.json.LocalDateTimeSerializer;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventStatus;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConcertReadDto {

    private final UUID uuid;
    private final String title;
    private final String description;
    private final LocalDateTime dtEvent;
    private final LocalDateTime dtEndOfSale;
    private final EventType type;
    private final EventStatus status;
    private final UUID category;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final LocalDateTime createdAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final LocalDateTime modifiedAt;
    private final String createdBy;
    private final String modifiedBy;
    private final Integer version;

    public ConcertReadDto(UUID uuid, String title, String description, LocalDateTime dtEvent, LocalDateTime dtEndOfSale, EventType type, EventStatus status, UUID category, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy, Integer version) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.dtEvent = dtEvent;
        this.dtEndOfSale = dtEndOfSale;
        this.type = type;
        this.status = status;
        this.category = category;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.version = version;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public EventType getType() {
        return type;
    }

    public EventStatus getStatus() {
        return status;
    }

    public UUID getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Integer getVersion() {
        return version;
    }
}
