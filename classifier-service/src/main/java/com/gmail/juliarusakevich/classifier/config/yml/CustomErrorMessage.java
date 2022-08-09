package com.gmail.juliarusakevich.classifier.config.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "error.message")
public class CustomErrorMessage {

    private String titleCountry;
    private String incorrectSymbols;
    private String countryNotFound;
    private String categoryNotFound;

    public String getCategoryNotFound() {
        return categoryNotFound;
    }

    public void setCategoryNotFound(String categoryNotFound) {
        this.categoryNotFound = categoryNotFound;
    }

    public String getTitleCountry() {
        return titleCountry;
    }

    public void setTitleCountry(String titleCountry) {
        this.titleCountry = titleCountry;
    }

    public String getIncorrectSymbols() {
        return incorrectSymbols;
    }

    public void setIncorrectSymbols(String incorrectSymbols) {
        this.incorrectSymbols = incorrectSymbols;
    }

    public String getCountryNotFound() {
        return countryNotFound;
    }

    public void setCountryNotFound(String countryNotFound) {
        this.countryNotFound = countryNotFound;
    }
}
