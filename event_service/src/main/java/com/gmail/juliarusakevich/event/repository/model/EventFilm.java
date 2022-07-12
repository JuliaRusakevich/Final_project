package com.gmail.juliarusakevich.event.repository.model;


import com.gmail.juliarusakevich.event.repository.model.enums.EventType;
import com.gmail.juliarusakevich.event.repository.model.enums.EventStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/*
country	string($uuid)
Страна (ссылка на справочник)

release_year	integer
example: 2022
Год

release_date	string($date)
example: 30 июня 2022
Дата премьеры

duration	integer
example: 100
Длительность (минуты)
 */
@Entity
@Table(name = "films", schema = "poster")
public class EventFilm extends Event {

    private UUID country;//ссылка на uuid county в двух кавычках передаем json
    private Integer releaseYear;
    private LocalDate releaseDate;
    private Integer duration;

    public EventFilm() {

    }

    public EventFilm(UUID country, Integer releaseYear, LocalDate releaseDate, Integer duration) {
        this.country = country;
        this.releaseYear = releaseYear;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public EventFilm(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title, String description, LocalDateTime dtEvent, LocalDateTime dtEndOfSale, EventType eventType, EventStatus eventStatus, UUID country, Integer releaseYear, LocalDate releaseDate, Integer duration) {
        super(uuid, dtCreate, dtUpdate, title, description, dtEvent, dtEndOfSale, eventType, eventStatus);
        this.country = country;
        this.releaseYear = releaseYear;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }



    public static FilmBuilder builder() {
        return new FilmBuilder();
    }

    @Column(name = "country", nullable = false)
    public UUID getCountry() {
        return country;
    }

    public void setCountry(UUID country) {
        this.country = country;
    }

    @Column(name = "release_year", nullable = false)
    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Column(name = "release_date", nullable = false)
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(name = "duration", nullable = false)
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public static class FilmBuilder {
        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private String title;
        private String description;
        private LocalDateTime dtEvent;
        private LocalDateTime dtEndOfSale;
        private EventType eventType;
        private EventStatus eventStatus;
        private UUID country;
        private Integer releaseYear;
        private LocalDate releaseDate;
        private Integer duration;

        FilmBuilder() {
        }

        public FilmBuilder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public FilmBuilder dtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public FilmBuilder dtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public FilmBuilder title(String title) {
            this.title = title;
            return this;
        }

        public FilmBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FilmBuilder dtEvent(LocalDateTime dtEvent) {
            this.dtEvent = dtEvent;
            return this;
        }

        public FilmBuilder dtEndOfSale(LocalDateTime dtEndOfSale) {
            this.dtEndOfSale = dtEndOfSale;
            return this;
        }

        public FilmBuilder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public FilmBuilder eventStatus(EventStatus eventStatus) {
            this.eventStatus = eventStatus;
            return this;
        }

        public FilmBuilder country(UUID country) {
            this.country = country;
            return this;
        }

        public FilmBuilder releaseYear(Integer releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public FilmBuilder releaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public FilmBuilder duration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public EventFilm build() {
            return new EventFilm(
                    uuid,
                    dtCreate,
                    dtUpdate,
                    title,
                    description,
                    dtEvent,
                    dtEndOfSale,
                    eventType,
                    eventStatus,
                    country,
                    releaseYear,
                    releaseDate,
                    duration);
        }

        public String toString() {
            return "Film.FilmBuilder(country=" + this.country + ", releaseYear=" + this.releaseYear + ", releaseDate=" + this.releaseDate + ", duration=" + this.duration + ")";
        }
    }
}
