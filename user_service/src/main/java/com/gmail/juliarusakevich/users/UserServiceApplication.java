package com.gmail.juliarusakevich.users;

import com.gmail.juliarusakevich.users.config.yml.CustomErrorMessage;
import com.gmail.juliarusakevich.users.config.yml.CustomPattern;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication(scanBasePackages = "com.gmail.juliarusakevich.users")
@EnableConfigurationProperties({CustomPattern.class, CustomErrorMessage.class})
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
