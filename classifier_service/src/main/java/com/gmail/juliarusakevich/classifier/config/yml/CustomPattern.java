package com.gmail.juliarusakevich.classifier.config.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "project.details.pattern")
public class CustomPattern {

    private String titleCountry;
    private String descriptionCountry;
    private String titleConcertCategory;

    @Bean("pattern1")
    public CustomPattern pattern1() {
        return new CustomPattern();
    }

    @Bean("pattern2")
    public CustomPattern pattern2() {
        return new CustomPattern();
    }


    public CustomPattern() {
    }

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
