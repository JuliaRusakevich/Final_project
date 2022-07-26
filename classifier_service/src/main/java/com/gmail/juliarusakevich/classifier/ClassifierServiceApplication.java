package com.gmail.juliarusakevich.classifier;

import com.gmail.juliarusakevich.classifier.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.classifier.config.yml.CustomPattern;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({CustomPattern.class, CustomErrorMessage.class})
public class ClassifierServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassifierServiceApplication.class, args);
    }

}
