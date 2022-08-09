package com.gmail.juliarusakevich.event.dto.film;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gmail.juliarusakevich.event.controller.json.LocalDateTimeSerializer;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventStatus;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventType;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.UUID;

public class FilmReadDto {

    private final UUID uuid;
    private final String title;
    private final String description;
    @JsonFormat(pattern="dd.MM.yyyy HH:mm")
    private final LocalDateTime dtEvent;
    @JsonFormat(pattern="dd.MM.yyyy HH:mm")
    private final LocalDateTime dtEndOfSale;
    private final EventType type;
    private final EventStatus status;
    private final UUID country;//ссылка на uuid county в двух кавычках передаем json
    private final Integer releaseYear;
    private final LocalDate releaseDate;
    private final Integer duration;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final LocalDateTime createdAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final LocalDateTime modifiedAt;
    private final String createdBy;
    private final String modifiedBy;
    private final Integer version;

    public FilmReadDto(UUID uuid, String title, String description, LocalDateTime dtEvent, LocalDateTime dtEndOfSale, EventType type, EventStatus status, UUID country, Integer releaseYear, LocalDate releaseDate, Integer duration, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy, Integer version) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.dtEvent = dtEvent;
        this.dtEndOfSale = dtEndOfSale;
        this.type = type;
        this.status = status;
        this.country = country;
        this.releaseYear = releaseYear;
        this.releaseDate = releaseDate;
        this.duration = duration;
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

    public UUID getCountry() {
        return country;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Integer getDuration() {
        return duration;
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
