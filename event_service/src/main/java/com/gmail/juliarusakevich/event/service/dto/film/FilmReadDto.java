package com.gmail.juliarusakevich.event.service.dto.film;

import com.gmail.juliarusakevich.event.repository.model.enums.EventStatus;
import com.gmail.juliarusakevich.event.repository.model.enums.EventType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class FilmReadDto {

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String title;
    private String description;
    private LocalDateTime dtEvent;
    private LocalDateTime dtEndOfSale;
    private EventType type;
    private EventStatus status;
    private UUID country;//ссылка на uuid county в двух кавычках передаем json
    private Integer releaseYear;
    private LocalDate releaseDate;
    private Integer duration;

    public FilmReadDto() {
    }

    public FilmReadDto(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title, String description, LocalDateTime dtEvent, LocalDateTime dtEndOfSale, EventType type, EventStatus status, UUID country, Integer releaseYear, LocalDate releaseDate, Integer duration) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
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

    public UUID getCountry() {
        return country;
    }

    public void setCountry(UUID country) {
        this.country = country;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmReadDto)) return false;
        FilmReadDto that = (FilmReadDto) o;
        return Objects.equals(getUuid(), that.getUuid()) && Objects.equals(getDtCreate(), that.getDtCreate()) && Objects.equals(getDtUpdate(), that.getDtUpdate()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDtEvent(), that.getDtEvent()) && Objects.equals(getDtEndOfSale(), that.getDtEndOfSale()) && getType() == that.getType() && getStatus() == that.getStatus() && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(getReleaseYear(), that.getReleaseYear()) && Objects.equals(getReleaseDate(), that.getReleaseDate()) && Objects.equals(getDuration(), that.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getDtCreate(), getDtUpdate(), getTitle(), getDescription(), getDtEvent(), getDtEndOfSale(), getType(), getStatus(), getCountry(), getReleaseYear(), getReleaseDate(), getDuration());
    }

    @Override
    public String toString() {
        return "FilmReadDto{" +
               "uuid=" + uuid +
               ", dtCreate=" + dtCreate +
               ", dtUpdate=" + dtUpdate +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", dtEvent=" + dtEvent +
               ", dtEndOfSale=" + dtEndOfSale +
               ", type=" + type +
               ", status=" + status +
               ", country=" + country +
               ", releaseYear=" + releaseYear +
               ", releaseDate=" + releaseDate +
               ", duration=" + duration +
               '}';
    }
}
