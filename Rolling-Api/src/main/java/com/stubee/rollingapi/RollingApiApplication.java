package com.stubee.rollingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.stubee.rollingcommons",
        "com.stubee.rollingexternal",
        "com.stubee.rollingservices",
        "com.stubee.rollingadapters",
        "com.stubee.rollingapi"})
public class RollingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RollingApiApplication.class, args);
    }

}