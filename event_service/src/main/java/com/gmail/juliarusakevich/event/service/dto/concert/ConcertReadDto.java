package com.gmail.juliarusakevich.event.service.dto.concert;

import com.gmail.juliarusakevich.event.repository.model.enums.EventStatus;
import com.gmail.juliarusakevich.event.repository.model.enums.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConcertReadDto {
    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String title;
    private String description;
    private LocalDateTime dtEvent;
    private LocalDateTime dtEndOfSale;
    private EventType type;
    private EventStatus status;
    private UUID category;

    public ConcertReadDto() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
    }

    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public void setDtEndOfSale(LocalDateTime dtEndOfSale) {
        this.dtEndOfSale = dtEndOfSale;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    @Override
    public String
    toString() {
        return "ConcertReadDto{" +
               "uuid=" + uuid +
               ", dtCreate=" + dtCreate +
               ", dtUpdate=" + dtUpdate +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", dtEvent=" + dtEvent +
               ", dtEndOfSale=" + dtEndOfSale +
               ", type=" + type +
               ", status=" + status +
               ", category=" + category +
               '}';
    }
}
