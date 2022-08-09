package com.gmail.juliarusakevich.users.config.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "project.details.pattern")
public class CustomPattern {

    private String mailPattern;
    private String nickPattern;
    private String passwordPattern;

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
}
