package com.stubee.rollingbatch.common.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = {
        "com.stubee.rollingadapter.common.annotation",
        "com.stubee.rollingadapter.common.config.querydsl",
        "com.stubee.rollingadapter.common.config.jpa",

        "com.stubee.rollingadapter.persistence",

        "com.stubee.rollingapplication.common",

        "com.stubee.rollingapplication.domain.company.service.query",
        "com.stubee.rollingapplication.domain.company.service.command",
        "com.stubee.rollingapplication.domain.review.service.query"
})
@EntityScan(basePackages = {"com.stubee.rollingadapter.persistence"})
@EnableJpaRepositories(basePackages = {"com.stubee.rollingadapter.persistence"})
public class BatchConfig {
}