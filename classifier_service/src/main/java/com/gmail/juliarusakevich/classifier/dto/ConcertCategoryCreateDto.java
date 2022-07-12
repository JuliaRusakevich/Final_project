package com.gmail.juliarusakevich.classifier.dto;

import java.util.Objects;

public class ConcertCategoryCreateDto {

    private String title;

    public ConcertCategoryCreateDto() {
    }

    public ConcertCategoryCreateDto(String title) {
        this.title = title;
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
        if (o == null || getClass() != o.getClass()) return false;
        ConcertCategoryCreateDto that = (ConcertCategoryCreateDto) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "ConcertCategoryCreateDto{" +
                "title='" + title + '\'' +
                '}';
    }

}
