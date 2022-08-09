package com.gmail.juliarusakevich.classifier.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
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
public class ConcertCategory
        extends AuditingEntity<UUID>
        implements Serializable {

    private static final long serialVersionUID = 2L;

    private UUID uuid;
    private String title;

    public ConcertCategory() {
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConcertCategory)) return false;
        ConcertCategory that = (ConcertCategory) o;
        return Objects.equals(getUuid(), that.getUuid()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getTitle());
    }

    @Override
    public String toString() {
        return "ConcertCategory{" +
               "uuid=" + uuid +
               ", title='" + title + '\'' +
               '}';
    }
}
