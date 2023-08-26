package com.stubee.companyaveragebatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.stubee.companyaveragebatch",
        "com.stubee.companyapplication.services.command",
        "com.stubee.companyapplication.services.query",

        "com.stubee.reviewapplication.services.query",

        "com.stubee.companypersistence",
        "com.stubee.reviewpersistence",
        "com.stubee.persistencecommons"
})
@EnableBatchProcessing
public class CompanyAverageBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyAverageBatchApplication.class, args);
    }

}