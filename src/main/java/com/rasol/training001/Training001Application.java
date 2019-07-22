package com.rasol.training001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Training001Application {

    public static void main(String[] args) {
        SpringApplication.run(Training001Application.class, args);
    }

}
