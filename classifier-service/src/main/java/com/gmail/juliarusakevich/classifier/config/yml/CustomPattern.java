package com.gmail.juliarusakevich.classifier.config.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "project.details.pattern")
public class CustomPattern {

    private String titleCountry;
    private String descriptionCountry;
    private String titleConcertCategory;

    public String getTitleCountry() {
        return titleCountry;
    }

    public void setTitleCountry(String titleCountry) {
        this.titleCountry = titleCountry;
    }

    public String getDescriptionCountry() {
        return descriptionCountry;
    }

    public void setDescriptionCountry(String descriptionCountry) {
        this.descriptionCountry = descriptionCountry;
    }

    public String getTitleConcertCategory() {
        return titleConcertCategory;
    }

    public void setTitleConcertCategory(String titleConcertCategory) {
        this.titleConcertCategory = titleConcertCategory;
    }
}
