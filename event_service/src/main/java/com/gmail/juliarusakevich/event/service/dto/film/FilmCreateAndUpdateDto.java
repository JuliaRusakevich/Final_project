package com.gmail.juliarusakevich.event.service.dto.film;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmail.juliarusakevich.event.repository.model.enums.EventStatus;
import com.gmail.juliarusakevich.event.repository.model.enums.EventType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


/*
{
  "title": "string",
  "description": "string",
  "dt_event": 0,
  "dt_end_of_sale": 0,
  "type": "FILMS",
  "status": "DRAFT",
  "country": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "release_year": 2022,
  "release_date": "30 июня 2022",
  "duration": 100
}
 */
public class FilmCreateAndUpdateDto {

    String title;
    String description;
    LocalDateTime dtEvent;
    LocalDateTime dtEndOfSale;
    EventType type;//FILMS
    EventStatus status; //DRAFT
    UUID country;
    Integer releaseYear;
    LocalDate releaseDate; //example: 30 июня 2022
    Integer duration;

    public FilmCreateAndUpdateDto() {
    }

    public FilmCreateAndUpdateDto(String title, String description, LocalDateTime dtEvent, LocalDateTime dtEndOfSale, EventType type, EventStatus status, UUID country, Integer releaseYear, LocalDate releaseDate, Integer duration) {
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

    @JsonFormat(pattern = "dd MMMM yyyy")
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
        if (!(o instanceof FilmCreateAndUpdateDto)) return false;
        FilmCreateAndUpdateDto dto = (FilmCreateAndUpdateDto) o;
        return Objects.equals(getTitle(), dto.getTitle()) && Objects.equals(getDescription(), dto.getDescription()) && Objects.equals(getDtEvent(), dto.getDtEvent()) && Objects.equals(getDtEndOfSale(), dto.getDtEndOfSale()) && getType() == dto.getType() && getStatus() == dto.getStatus() && Objects.equals(getCountry(), dto.getCountry()) && Objects.equals(getReleaseYear(), dto.getReleaseYear()) && Objects.equals(getReleaseDate(), dto.getReleaseDate()) && Objects.equals(getDuration(), dto.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getDtEvent(), getDtEndOfSale(), getType(), getStatus(), getCountry(), getReleaseYear(), getReleaseDate(), getDuration());
    }

    @Override
    public String toString() {
        return "FilmCreateAndUpdateDto{" +
               "title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", dtEvent=" + dtEvent +
               ", dtEndOfSale=" + dtEndOfSale +
               ", type=" + type +
               ", status=" + status +
               ", uuidCountry='" + country + '\'' +
               ", releaseYear=" + releaseYear +
               ", releaseDate=" + releaseDate +
               ", duration=" + duration +
               '}';
    }
}


