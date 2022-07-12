package com.gmail.juliarusakevich.event.repository.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gmail.juliarusakevich.event.controller.json.LocalDateTimeDeserializer;
import com.gmail.juliarusakevich.event.controller.json.LocalDateTimeSerializer;
import com.gmail.juliarusakevich.event.repository.model.enums.EventType;
import com.gmail.juliarusakevich.event.repository.model.enums.EventStatus;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/*
description:
Событие

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
Наименование

description	string
Описание

dt_event	integer($int64)
Дата/время проведения (linux time)

dt_end_of_sale	integer($int64)
Дата/время окончания продажи билетов (linux time)

type	EventTypestring
Тип события:

FILMS - Фильм
CONCERTS - Концерт
Enum:
Array [ 2 ]
status	EventStatusstring
Статус события:

DRAFT - Черновик
PUBLISHED - Опубликовано
Enum:
Array [ 2 ]

}
 */

/*
Свойства суперкласса по умолчанию будут проигнорированы.
Чтобы сохранить их в таблицу конкретного подкласса, необходимо использовать
аннотацию @MappedSuperClass.

Стратегия №4 (JOINED) подойдет в случаях, когда требуются полиморфные запросы
и ассоциации, но подклассы объявляют относительно много новых полей.
 */

@Entity
@Table(name = "events", schema = "poster")
@Inheritance(strategy = InheritanceType.JOINED)
public class Event implements Serializable {

    private static final long serialVersionUID = 2L;

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String title;
    private String description;
    private LocalDateTime dtEvent;
    private LocalDateTime dtEndOfSale;
    private EventType type;
    private EventStatus status;

    public Event() {
    }

    public Event(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title, String description, LocalDateTime dtEvent, LocalDateTime dtEndOfSale, EventType eventType, EventStatus eventStatus) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.description = description;
        this.dtEvent = dtEvent;
        this.dtEndOfSale = dtEndOfSale;
        this.type = eventType;
        this.status = eventStatus;
    }
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Column(name = "dt_create", updatable=false)
    @ReadOnlyProperty
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Version
    @Column(name = "dt_update")
   // @ReadOnlyProperty
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "dt_event", nullable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
    }

    @Column(name = "dt_end_of_sale", nullable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public void setDtEndOfSale(LocalDateTime dtEndOfSale) {
        this.dtEndOfSale = dtEndOfSale;
    }

    //@Column(name = "type", updatable = false, insertable = false)
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public EventType getType() {
        return type;
    }

    public void setType(EventType eventType) {
        this.type = eventType;
    }

    //???????????????????????????????
  //  @Column(name = "status", updatable = false, insertable = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus eventStatus) {
        this.status = eventStatus;
    }
}
