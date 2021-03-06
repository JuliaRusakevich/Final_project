package com.gmail.juliarusakevich.classifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.gmail.juliarusakevich.classifier.repository")
@EntityScan("com.gmail.juliarusakevich.classifier.model")
public class ClassifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassifierApplication.class, args);
    }
}
