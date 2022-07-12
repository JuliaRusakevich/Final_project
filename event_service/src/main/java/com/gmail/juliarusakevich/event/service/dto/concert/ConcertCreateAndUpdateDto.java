package com.gmail.juliarusakevich.event.service.dto.concert;

import com.gmail.juliarusakevich.event.repository.model.enums.EventStatus;
import com.gmail.juliarusakevich.event.repository.model.enums.EventType;

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

    public ConcertCreateAndUpdateDto(
            String title,
            String description,
            LocalDateTime dtEvent,
            LocalDateTime dtEndOfSale,
            EventType type,
            EventStatus status,
            UUID category) {
        this.title = title;
        this.description = description;
        this.dtEvent = dtEvent;
        this.dtEndOfSale = dtEndOfSale;
        this.type = type;
        this.status = status;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConcertCreateAndUpdateDto)) return false;
        ConcertCreateAndUpdateDto that = (ConcertCreateAndUpdateDto) o;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDtEvent(), that.getDtEvent()) && Objects.equals(dtEndOfSale, that.dtEndOfSale) && getType() == that.getType() && getStatus() == that.getStatus() && Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getDtEvent(), dtEndOfSale, getType(), getStatus(), getCategory());
    }

    @Override
    public String toString() {
        return "ConcertCreateAndUpdateDto{" +
               "title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", dtEvent=" + dtEvent +
               ", dtEndOfSale=" + dtEndOfSale +
               ", type=" + type +
               ", status=" + status +
               ", category=" + category +
               '}';
    }
}
