package com.gmail.juliarusakevich.event.dto.concert;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventStatus;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class ConcertCreateAndUpdateDto {

    String title;
    String description;
    LocalDateTime dtEvent;
    LocalDateTime dtEndOfSale;
    EventType type;//CONCERTS
    EventStatus status; //DRAFT
    UUID category;

    public ConcertCreateAndUpdateDto() {
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
    @JsonFormat(pattern="dd.MM.yyyy HH:mm")
    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
    }
    @JsonFormat(pattern="dd.MM.yyyy HH:mm")
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
}
