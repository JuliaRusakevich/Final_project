package com.gmail.juliarusakevich.event.repository.entity;



import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "concerts", schema = "poster")
public class EventConcert extends Event {

    private UUID category;

    public EventConcert() {
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventConcert)) return false;
        EventConcert that = (EventConcert) o;
        return Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory());
    }

    @Override
    public String toString() {
        return "EventConcert{" +
               "category=" + category +
               '}';
    }
}
