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
ConcertCategory{
description:
Категория концерта

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
example: Стендап
Название
}
 */
@Entity
@Table(name = "concert_category", schema = "classifier")
public class ConcertCategory implements Serializable {

    private static final long serialVersionUID = 2L;
    @Id
    @Column(name = "uuid", nullable = false, updatable = false)
    private UUID uuid;
    @Column(name = "dt_create", nullable = false)
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update", nullable = false)
    private LocalDateTime dtUpdate;
    @Column(name = "title")
    private String title;

    public ConcertCategory() {
    }

    public ConcertCategory(UUID uuid, LocalDateTime dt_create, LocalDateTime dt_update, String title) {
        this.uuid = uuid;
        this.dtCreate = dt_create;
        this.dtUpdate = dt_update;
        this.title = title;
    }

    public static ConcertCategoryBuilder builder() {
        return new ConcertCategoryBuilder();
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

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcertCategory that = (ConcertCategory) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(dtCreate, that.dtCreate) && Objects.equals(dtUpdate, that.dtUpdate) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, title);
    }

    @Override
    public String toString() {
        return "ConcertCategory{" +
                "uuid=" + uuid +
                ", dt_create=" + dtCreate +
                ", dt_update=" + dtUpdate +
                ", title='" + title + '\'' +
                '}';
    }

    public static class ConcertCategoryBuilder {

        private UUID uuid;
        private LocalDateTime dt_create;
        private LocalDateTime dt_update;
        private String title;

        public ConcertCategoryBuilder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public ConcertCategoryBuilder dt_create(LocalDateTime dt_create) {
            this.dt_create = dt_create;
            return this;
        }

        public ConcertCategoryBuilder dt_update(LocalDateTime dt_update) {
            this.dt_update = dt_update;
            return this;
        }

        public ConcertCategoryBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ConcertCategory build() {
            return new ConcertCategory(uuid, dt_create, dt_update, title);
        }

        @Override
        public String toString() {
            return "ConcertCategoryBuilder{" +
                    "uuid=" + uuid +
                    ", dt_create=" + dt_create +
                    ", dt_update=" + dt_update +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
