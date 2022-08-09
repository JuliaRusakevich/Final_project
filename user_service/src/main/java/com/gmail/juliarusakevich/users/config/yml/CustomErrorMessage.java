package com.gmail.juliarusakevich.users.config.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "error.message")
public class CustomErrorMessage {

    private String incorrectMail;
    private String incorrectNick;
    private String incorrectPassword;
    private String userNotFound;
    private String updatedInfo;

    public String getIncorrectMail() {
        return incorrectMail;
    }

    public void setIncorrectMail(String incorrectMail) {
        this.incorrectMail = incorrectMail;
    }

    public String getIncorrectNick() {
        return incorrectNick;
    }

    public void setIncorrectNick(String incorrectNick) {
        this.incorrectNick = incorrectNick;
    }

    public String getIncorrectPassword() {
        return incorrectPassword;
    }

    public void setIncorrectPassword(String incorrectPassword) {
        this.incorrectPassword = incorrectPassword;
    }

    public String getUserNotFound() {
        return userNotFound;
    }

    public void setUserNotFound(String userNotFound) {
        this.userNotFound = userNotFound;
    }

    public String getUpdatedInfo() {
        return updatedInfo;
    }

    public void setUpdatedInfo(String updatedInfo) {
        this.updatedInfo = updatedInfo;
    }
}
