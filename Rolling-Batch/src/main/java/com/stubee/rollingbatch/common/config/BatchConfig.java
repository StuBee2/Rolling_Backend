package com.stubee.rollingbatch.common.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = {"com.stubee.rollingapplication.common",
        "com.stubee.rollingapplication.domain.company",
        "com.stubee.rollingapplication.domain.member",
        "com.stubee.rollingapplication.domain.review",

        "com.stubee.rollinginfrastructure.common.security.adapter",

        "com.stubee.rollingadapter.out"})
@EntityScan(basePackages = {"com.stubee.rollingadapter.out"})
@EnableJpaRepositories(basePackages = {"com.stubee.rollingadapter"})
public class BatchConfig {
}