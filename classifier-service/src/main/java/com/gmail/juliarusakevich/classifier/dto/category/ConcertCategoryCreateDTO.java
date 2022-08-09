package com.gmail.juliarusakevich.classifier.dto.category;

import java.util.Objects;

public class ConcertCategoryCreateDTO {

    private String title;

    public ConcertCategoryCreateDTO() {
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
        return Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public String toString() {
        return "ConcertCategoryCreateDTO{" +
               "title='" + title + '\'' +
               '}';
    }
}
