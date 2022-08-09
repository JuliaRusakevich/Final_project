package com.gmail.juliarusakevich.event.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "films", schema = "poster")
public class EventFilm extends Event {

    @Column(name = "country")
    private UUID country;//ссылка на uuid county в двух кавычках передаем json
    @Column(name = "release_year")
    private Integer releaseYear;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(name = "duration")
    private Integer duration;

    public EventFilm() {
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
}
