package com.gmail.juliarusakevich.classifier.config.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "error.message")
public class CustomErrorMessage {

    private String titleCountry;
    private String incorrectSymbols;

    @Bean
    public CustomErrorMessage errorMessage1(){
        return new CustomErrorMessage();
    }

    @Bean
    public CustomErrorMessage errorMessage2(){
        return new CustomErrorMessage();
    }


    public CustomErrorMessage() {
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
}
