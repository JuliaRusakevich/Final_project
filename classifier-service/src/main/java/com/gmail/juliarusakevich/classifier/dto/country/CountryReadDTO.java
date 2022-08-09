package com.gmail.juliarusakevich.classifier.dto.country;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class CountryReadDTO {

    private final UUID uuid;
    private final String title;
    private final String description;
    public final LocalDateTime createdAt;
    public final LocalDateTime modifiedAt;
    public final String createdBy;
    public final String modifiedBy;


    public CountryReadDTO(UUID uuid, String title, String description, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    @Override
    public String toString() {
        return "CountryReadDTO{" +
               "uuid=" + uuid +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", createdAt=" + createdAt +
               ", modifiedAt=" + modifiedAt +
               ", createdBy='" + createdBy + '\'' +
               ", modifiedBy='" + modifiedBy + '\'' +
               '}';
    }
}
