package com.gmail.juliarusakevich.event.config.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "project.details.url")
public class ProjectDetailsUrl {

    private String country;
    private String concertCategory;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getConcertCategory() {
        return concertCategory;
    }

    public void setConcertCategory(String concertCategory) {
        this.concertCategory = concertCategory;
    }
}
