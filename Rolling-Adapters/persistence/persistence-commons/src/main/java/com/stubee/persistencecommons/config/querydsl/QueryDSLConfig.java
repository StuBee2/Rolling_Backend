package com.stubee.persistencecommons.config.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDSLConfig {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory init() {
        return new JPAQueryFactory(em);
    }

}