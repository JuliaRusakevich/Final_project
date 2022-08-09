package com.gmail.juliarusakevich.classifier.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "country", schema = "classifier")
public class Country
        extends AuditingEntity<UUID>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID uuid;

    private String title;
    private String description;

    public Country() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return Objects.equals(getUuid(), country.getUuid()) && Objects.equals(getTitle(), country.getTitle()) && Objects.equals(getDescription(), country.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getTitle(), getDescription());
    }

    @Override
    public String toString() {
        return "Country{" +
               "uuid=" + uuid +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}

