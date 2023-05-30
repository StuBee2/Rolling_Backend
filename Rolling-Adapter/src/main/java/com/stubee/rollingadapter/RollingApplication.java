package com.stubee.rollingadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RollingApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.stubee.rollingadapter.RollingApplication.class, args);
    }

}