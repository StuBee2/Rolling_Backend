package com.stubee.loggingpersistence.mapper;

import com.stubee.persistencecommons.annotations.DomainObjectMapper;
import com.stubee.persistencecommons.entity.LoggingEntity;
import com.stubee.rollingdomains.domain.logging.model.Logging;

@DomainObjectMapper
public class LoggingMapper implements com.stubee.persistencecommons.mapper.DomainObjectMapper<LoggingEntity, Logging> {

    @Override
    public LoggingEntity toEntity(final Logging domain) {
        return LoggingEntity.builder()
                .description(domain.description())
                .module(domain.module())
                .memberId(domain.memberId().getId())
                .build();
    }

    @Override
    public Logging toDomain(final LoggingEntity entity) {
        return Logging.createWithLoggingId(entity.getId(), entity.getDescription(),
                entity.getModule(), entity.getMemberId(), entity.getCreatedAt());
    }

}