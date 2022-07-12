package com.gmail.juliarusakevich.classifier.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class ConcertCategoryReadDto {

    private final UUID uuid;
    private final LocalDateTime dtCreate;
    private final LocalDateTime dtUpdate;
    private final String title;

    public ConcertCategoryReadDto(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
    }

    public static ConcertCategoryReadDtoBuilder builder() {
        return new ConcertCategoryReadDtoBuilder();
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
        ConcertCategoryReadDto that = (ConcertCategoryReadDto) o;
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

    public static class ConcertCategoryReadDtoBuilder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private String title;

        public ConcertCategoryReadDtoBuilder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public ConcertCategoryReadDtoBuilder dtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public ConcertCategoryReadDtoBuilder dtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public ConcertCategoryReadDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ConcertCategoryReadDto build() {
            return new ConcertCategoryReadDto(uuid, dtCreate, dtUpdate, title);
        }

        @Override
        public String toString() {
            return "ConcertCategoryReadDtoBuilder{" +
                    "uuid=" + uuid +
                    ", dtCreate=" + dtCreate +
                    ", dtUpdate=" + dtUpdate +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
