package com.gmail.juliarusakevich.event.config.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "error.message")
public class CustomErrorMessage {

    private String titleCountry;
    private String incorrectSymbols;
    private String countryNotFound;
    private String categoryNotFound;
    private String connectionFail;
    private String incorrectDate;
    private String accountNotActivated;
    private String concertNotFound;
    private String filmNotFound;
    private String updatedInfo;

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

    public String getCategoryNotFound() {
        return categoryNotFound;
    }

    public void setCategoryNotFound(String categoryNotFound) {
        this.categoryNotFound = categoryNotFound;
    }

    public String getConnectionFail() {
        return connectionFail;
    }

    public void setConnectionFail(String connectionFail) {
        this.connectionFail = connectionFail;
    }

    public String getIncorrectDate() {
        return incorrectDate;
    }

    public void setIncorrectDate(String incorrectDate) {
        this.incorrectDate = incorrectDate;
    }

    public String getAccountNotActivated() {
        return accountNotActivated;
    }

    public void setAccountNotActivated(String accountNotActivated) {
        this.accountNotActivated = accountNotActivated;
    }

    public String getConcertNotFound() {
        return concertNotFound;
    }

    public void setConcertNotFound(String concertNotFound) {
        this.concertNotFound = concertNotFound;
    }

    public String getFilmNotFound() {
        return filmNotFound;
    }

    public void setFilmNotFound(String filmNotFound) {
        this.filmNotFound = filmNotFound;
    }

    public String getUpdatedInfo() {
        return updatedInfo;
    }

    public void setUpdatedInfo(String updatedInfo) {
        this.updatedInfo = updatedInfo;
    }
}
