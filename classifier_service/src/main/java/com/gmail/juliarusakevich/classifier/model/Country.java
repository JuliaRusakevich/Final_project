package com.gmail.juliarusakevich.classifier.model;


import com.gmail.juliarusakevich.classifier.controller.json.LocalDateTimeDeserializer;
import com.gmail.juliarusakevich.classifier.controller.json.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/*
Country{
description:
Страна
uuid*	string($uuid)
readOnly: true
Уникальный идентификатор сущности

dt_create*	number($int64)
readOnly: true
Дата создания сущности (linux time)

dt_update*	integer($int64)
readOnly: true
Дата последнего обновления сущности (linux time)

title	string
example: RUS
Название

description	string
example: Россия
Расшифровка

}
 */
@Entity
@Table(name = "country", schema = "classifier")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "uuid", nullable = false, updatable = false)
    private UUID uuid;
    @Column(name = "dt_create", nullable = false)
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update", nullable = false)
    private LocalDateTime dtUpdate;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;

    public Country() {
    }

    public Country(UUID uuid,
                   LocalDateTime dtCreate,
                   LocalDateTime dtUpdate,
                   String title,
                   String description) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.description = description;
    }

    public static CountryBuilder builder() {
        return new CountryBuilder();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }


    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(uuid, country.uuid) && Objects.equals(dtCreate, country.dtCreate) && Objects.equals(dtUpdate, country.dtUpdate) && Objects.equals(title, country.title) && Objects.equals(description, country.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, title, description);
    }

    @Override
    public String toString() {
        return "Country{}";
    }

    public static class CountryBuilder {
        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private String title;
        private String description;

        CountryBuilder() {
        }

        public CountryBuilder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public CountryBuilder dtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public CountryBuilder dtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public CountryBuilder title(String title) {
            this.title = title;
            return this;
        }

        public CountryBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Country build() {
            return new Country(uuid, dtCreate, dtUpdate, title, description);
        }

        public String toString() {
            return "Country.CountryBuilder(uuid=" + this.uuid + ", dtCreate=" + this.dtCreate + ", dtUpdate=" + this.dtUpdate + ", title=" + this.title + ", description=" + this.description + ")";
        }
    }
}
