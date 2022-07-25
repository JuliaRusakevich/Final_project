package com.gmail.juliarusakevich.users.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "project.details")
public class ProjectDetails {

    public String mailPattern;
    public String nickPattern;
    public String passwordPattern;

    public String mailField;
    public String nickField;
    public String passwordField;

    public String mailErrorMessage;
    public String nickErrorMessage;
    public String passwordErrorMessage;

    public String getMailPattern() {
        return mailPattern;
    }

    public void setMailPattern(String mailPattern) {
        this.mailPattern = mailPattern;
    }

    public String getNickPattern() {
        return nickPattern;
    }

    public void setNickPattern(String nickPattern) {
        this.nickPattern = nickPattern;
    }

    public String getPasswordPattern() {
        return passwordPattern;
    }

    public void setPasswordPattern(String passwordPattern) {
        this.passwordPattern = passwordPattern;
    }

    public String getMailField() {
        return mailField;
    }

    public void setMailField(String mailField) {
        this.mailField = mailField;
    }

    public String getNickField() {
        return nickField;
    }

    public void setNickField(String nickField) {
        this.nickField = nickField;
    }

    public String getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(String passwordField) {
        this.passwordField = passwordField;
    }

    public String getMailErrorMessage() {
        return mailErrorMessage;
    }

    public void setMailErrorMessage(String mailErrorMessage) {
        this.mailErrorMessage = mailErrorMessage;
    }

    public String getNickErrorMessage() {
        return nickErrorMessage;
    }

    public void setNickErrorMessage(String nickErrorMessage) {
        this.nickErrorMessage = nickErrorMessage;
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage;
    }

    public void setPasswordErrorMessage(String passwordErrorMessage) {
        this.passwordErrorMessage = passwordErrorMessage;
    }
}

