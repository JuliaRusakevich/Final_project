package com.gmail.juliarusakevich.classifier.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CountryCreateDto {

    private String title;
    private String description;

    @JsonCreator
    CountryCreateDto(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description) {
        this.title = title;
        this.description = description;
    }

    public static CountryCreateDtoBuilder builder() {
        return new CountryCreateDtoBuilder();
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
        CountryCreateDto that = (CountryCreateDto) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }

    @Override
    public String toString() {
        return "CountryCreateDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class CountryCreateDtoBuilder {
        private String title;
        private String description;

        CountryCreateDtoBuilder() {
        }

        public CountryCreateDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public CountryCreateDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CountryCreateDto build() {
            return new CountryCreateDto(title, description);
        }

        public String toString() {
            return "CountryCreateDto.CountryCreateDtoBuilder(title=" + this.title + ", description=" + this.description + ")";
        }
    }

}
