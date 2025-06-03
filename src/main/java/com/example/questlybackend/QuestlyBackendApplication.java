package com.example.questlybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuestlyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestlyBackendApplication.class, args);
    }

}
