package com.stubee.rollingbatch.common.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.stubee.companyapplication.services.command",
        "com.stubee.companyapplication.services.query",

        "com.stubee.reviewapplication.services.query",

        "com.stubee.companypersistence",
        "com.stubee.reviewpersistence",
        "com.stubee.persistencecommons"
})
@EnableBatchProcessing
public class BatchConfig {
}