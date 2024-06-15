package com.perfume.haven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.perfume.haven.repository")
public class HavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(HavenApplication.class, args);
    }
}

