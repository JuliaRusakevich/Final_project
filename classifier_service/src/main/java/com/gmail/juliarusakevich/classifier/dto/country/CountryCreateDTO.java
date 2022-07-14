package com.gmail.juliarusakevich.classifier.dto.country;

import java.time.LocalDateTime;
import java.util.Objects;

public class CountryCreateDTO {

    private LocalDateTime dtCreate;
    private String title;
    private String description;

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
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
        if (!(o instanceof CountryCreateDTO)) return false;
        CountryCreateDTO that = (CountryCreateDTO) o;
        return Objects.equals(getDtCreate(), that.getDtCreate()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDtCreate(), getTitle(), getDescription());
    }

    @Override
    public String toString() {
        return "CountryCreateDTO{" +
               "dtCreate=" + dtCreate +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
