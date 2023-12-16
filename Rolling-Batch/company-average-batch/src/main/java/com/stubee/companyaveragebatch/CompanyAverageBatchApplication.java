package com.stubee.companyaveragebatch;

import jakarta.annotation.PostConstruct;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan(basePackages = {
        "com.stubee.companyaveragebatch",

        "com.stubee.companyapplication.services",
        "com.stubee.companyapplication.usecases.command.impl.batch",
        "com.stubee.companyapplication.usecases.query.impl",

        "com.stubee.reviewapplication.services",
        "com.stubee.reviewapplication.usecases.query.impl.batch",

        "com.stubee.companypersistence",
        "com.stubee.reviewpersistence",
        "com.stubee.persistencecommons",

        "com.stubee.adapterscommons"
})
public class CompanyAverageBatchApplication {

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication.run(CompanyAverageBatchApplication.class, args);
    }

}