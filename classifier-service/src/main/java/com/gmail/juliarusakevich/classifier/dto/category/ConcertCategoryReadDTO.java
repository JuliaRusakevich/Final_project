package com.gmail.juliarusakevich.classifier.dto.category;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class ConcertCategoryReadDTO {

    private final UUID uuid;
    private final String title;
    public final LocalDateTime createdAt;
    public final LocalDateTime modifiedAt;
    public final String createdBy;
    public final String modifiedBy;

    public ConcertCategoryReadDTO(UUID uuid, String title, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        this.uuid = uuid;
        this.title = title;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConcertCategoryReadDTO)) return false;
        ConcertCategoryReadDTO that = (ConcertCategoryReadDTO) o;
        return Objects.equals(getUuid(), that.getUuid()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getModifiedAt(), that.getModifiedAt()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getModifiedBy(), that.getModifiedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getTitle(), getCreatedAt(), getModifiedAt(), getCreatedBy(), getModifiedBy());
    }

    @Override
    public String toString() {
        return "ConcertCategoryReadDTO{" +
               "uuid=" + uuid +
               ", title='" + title + '\'' +
               ", createdAt=" + createdAt +
               ", modifiedAt=" + modifiedAt +
               ", createdBy='" + createdBy + '\'' +
               ", modifiedBy='" + modifiedBy + '\'' +
               '}';
    }
}
