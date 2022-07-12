package com.gmail.juliarusakevich.event.repository.model;

import com.gmail.juliarusakevich.event.repository.model.enums.EventType;
import com.gmail.juliarusakevich.event.repository.model.enums.EventStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;


/*
EventConcert{
description:
Концерт

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
category	string($uuid)
Категория (ссылка на справочник)

}
 */

@Entity
@Table(name = "concerts", schema = "poster")
public class EventConcert extends Event {

    private UUID category;

    public EventConcert() {
    }

    public EventConcert(UUID category) {
        this.category = category;
    }

    public EventConcert(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title, String description, LocalDateTime dtEvent, LocalDateTime dtEndOfSale, EventType eventType, EventStatus eventStatus, UUID category) {
        super(uuid, dtCreate, dtUpdate, title, description, dtEvent, dtEndOfSale, eventType, eventStatus);
        this.category = category;
    }

    public EventConcert(UUID uuid,
                        LocalDateTime dtCreate,
                        LocalDateTime dtUpdate,
                        String title,
                        String description,
                        LocalDateTime dtEvent,
                        LocalDateTime dtEndOfSale,
                        EventType eventType,
                        EventStatus eventStatus) {
        super(uuid, dtCreate, dtUpdate, title, description, dtEvent, dtEndOfSale, eventType, eventStatus);
    }

    public static ConcertBuilder builder() {
        return new ConcertBuilder();
    }


    @Column(name = "category", nullable = false)
    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    public static class ConcertBuilder {
        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private String title;
        private String description;
        private LocalDateTime dtEvent;
        private LocalDateTime dtEndOfSale;
        private EventType eventType;
        private EventStatus eventStatus;
        private UUID category;

        ConcertBuilder() {
        }

        public ConcertBuilder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public ConcertBuilder dtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public ConcertBuilder dtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public ConcertBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ConcertBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ConcertBuilder dtEvent(LocalDateTime dtEvent) {
            this.dtEvent = dtEvent;
            return this;
        }

        public ConcertBuilder dtEndOfSale(LocalDateTime dtEndOfSale) {
            this.dtEndOfSale = dtEndOfSale;
            return this;
        }

        public ConcertBuilder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public ConcertBuilder eventStatus(EventStatus eventStatus) {
            this.eventStatus = eventStatus;
            return this;
        }

        public ConcertBuilder category(UUID category) {
            this.category = category;
            return this;
        }

        public EventConcert build(){
            return new EventConcert(
                    uuid,
                    dtCreate,
                    dtUpdate,
                    title,
                    description,
                    dtEvent,
                    dtEndOfSale,
                    eventType,
                    eventStatus,
                    category);
        }

        @Override
        public String toString() {
            return "ConcertBuilder{" +
                   "uuid=" + uuid +
                   ", dtCreate=" + dtCreate +
                   ", dtUpdate=" + dtUpdate +
                   ", title='" + title + '\'' +
                   ", description='" + description + '\'' +
                   ", dtEvent=" + dtEvent +
                   ", dtEndOfSale=" + dtEndOfSale +
                   ", eventType=" + eventType +
                   ", eventStatus=" + eventStatus +
                   ", category=" + category +
                   '}';
        }
    }
}
