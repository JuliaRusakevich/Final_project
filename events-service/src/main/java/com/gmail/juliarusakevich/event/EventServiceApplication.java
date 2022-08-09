package com.gmail.juliarusakevich.event;

import com.gmail.juliarusakevich.event.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.event.config.yml.CustomPattern;
import com.gmail.juliarusakevich.event.config.yml.ProjectDetailsUrl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "com.gmail.juliarusakevich.event")
@EnableConfigurationProperties({CustomPattern.class, CustomErrorMessage.class, ProjectDetailsUrl.class})
public class EventServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventServiceApplication.class, args);
    }

}
