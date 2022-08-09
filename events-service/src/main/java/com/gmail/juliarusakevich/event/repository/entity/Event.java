package com.gmail.juliarusakevich.event.repository.entity;

import com.gmail.juliarusakevich.event.repository.entity.enums.EventType;
import com.gmail.juliarusakevich.event.repository.entity.enums.EventStatus;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

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
public class Event
        extends AuditingEntity<UUID>
        implements Serializable {

    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uuid;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "dt_event")
    private LocalDateTime dtEvent;
    @Column(name = "dt_end_of_sale")
    private LocalDateTime dtEndOfSale;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EventType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EventStatus status;
    @Version
    @Column(name = "version")
    private Integer version;

    public Event() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
