package com.gmail.juliarusakevich.classifier.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class CountryReadDto {

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String title;
    private String description;

    public CountryReadDto() {
    }

    public CountryReadDto(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title, String description) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.description = description;
    }

    public static CountryReadDtoBuilder builder() {
        return new CountryReadDtoBuilder();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryReadDto that = (CountryReadDto) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(dtCreate, that.dtCreate) && Objects.equals(dtUpdate, that.dtUpdate) && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, title, description);
    }

    @Override
    public String toString() {
        return "CountryReadDto{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class CountryReadDtoBuilder {
        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private String title;
        private String description;

        CountryReadDtoBuilder() {
        }

        public CountryReadDtoBuilder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public CountryReadDtoBuilder dtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public CountryReadDtoBuilder dtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public CountryReadDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public CountryReadDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CountryReadDto build() {
            return new CountryReadDto(uuid, dtCreate, dtUpdate, title, description);
        }

        public String toString() {
            return "CountryReadDto.CountryReadDtoBuilder(uuid=" + this.uuid + ", dtCreate=" + this.dtCreate + ", dtUpdate=" + this.dtUpdate + ", title=" + this.title + ", description=" + this.description + ")";
        }
    }
}
