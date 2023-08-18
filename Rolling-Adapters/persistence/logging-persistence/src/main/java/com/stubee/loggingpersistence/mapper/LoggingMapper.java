package com.stubee.loggingpersistence.mapper;

import com.stubee.persistencecommons.commons.annotations.Mapper;
import com.stubee.persistencecommons.commons.entity.LoggingEntity;
import com.stubee.persistencecommons.commons.mapper.DomainObjectMapper;
import com.stubee.rollingdomains.domain.logging.model.Logging;

@Mapper
public class LoggingMapper implements DomainObjectMapper<LoggingEntity, Logging> {

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