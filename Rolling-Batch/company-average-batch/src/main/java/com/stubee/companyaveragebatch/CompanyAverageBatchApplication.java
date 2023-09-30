package com.stubee.companyaveragebatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan(basePackages = {
        "com.stubee.companyaveragebatch",

        "com.stubee.companyapplication.services",
        "com.stubee.companyapplication.usecases.command.impl.batch",
        "com.stubee.companyapplication.usecases.query.impl",

        "com.stubee.reviewapplication.services",
        "com.stubee.reviewapplication.usecases.query.impl.shared",

        "com.stubee.companypersistence",
        "com.stubee.reviewpersistence",
        "com.stubee.persistencecommons"
})
public class CompanyAverageBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyAverageBatchApplication.class, args);
    }

}