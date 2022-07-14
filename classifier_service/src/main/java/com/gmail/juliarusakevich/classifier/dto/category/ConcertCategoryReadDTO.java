package com.gmail.juliarusakevich.classifier.dto.category;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class ConcertCategoryReadDTO {

    private final UUID uuid;
    private final LocalDateTime dtCreate;
    private final LocalDateTime dtUpdate;
    private final String title;

    public ConcertCategoryReadDTO(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcertCategoryReadDTO that = (ConcertCategoryReadDTO) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(dtCreate, that.dtCreate) && Objects.equals(dtUpdate, that.dtUpdate) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, title);
    }

    @Override
    public String toString() {
        return "ConcertCategoryReadDto{" +
               "uuid=" + uuid +
               ", dtCreate=" + dtCreate +
               ", dtUpdate=" + dtUpdate +
               ", title='" + title + '\'' +
               '}';
    }

}
