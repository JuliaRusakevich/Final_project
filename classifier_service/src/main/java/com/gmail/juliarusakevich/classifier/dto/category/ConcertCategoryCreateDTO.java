package com.gmail.juliarusakevich.classifier.dto.category;

import java.time.LocalDateTime;
import java.util.Objects;

public class ConcertCategoryCreateDTO {

    private LocalDateTime dtCreate;
    private String title;

    public ConcertCategoryCreateDTO() {
    }

    public ConcertCategoryCreateDTO(LocalDateTime dtCreate, String title) {
        this.dtCreate = dtCreate;
        this.title = title;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConcertCategoryCreateDTO)) return false;
        ConcertCategoryCreateDTO that = (ConcertCategoryCreateDTO) o;
        return Objects.equals(getDtCreate(), that.getDtCreate()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDtCreate(), getTitle());
    }

    @Override
    public String toString() {
        return "ConcertCategoryCreateDTO{" +
               "dtCreate=" + dtCreate +
               ", title='" + title + '\'' +
               '}';
    }
}
