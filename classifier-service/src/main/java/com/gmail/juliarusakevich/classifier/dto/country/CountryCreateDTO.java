package com.gmail.juliarusakevich.classifier.dto.country;

import java.util.Objects;

public class CountryCreateDTO {

    private String title;
    private String description;

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
        if (!(o instanceof CountryCreateDTO)) return false;
        CountryCreateDTO that = (CountryCreateDTO) o;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription());
    }

    @Override
    public String toString() {
        return "CountryCreateDTO{" +
               "title='" + title + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
