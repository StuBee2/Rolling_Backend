package com.stubee.rollingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.stubee.rollingapi",
        "com.stubee.rollingservices",
        "com.stubee.persistence",
        "com.stubee.thirdparty"})
public class RollingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RollingApiApplication.class, args);
    }

}