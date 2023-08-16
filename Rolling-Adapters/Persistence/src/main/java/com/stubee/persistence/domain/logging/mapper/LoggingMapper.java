package com.stubee.persistence.domain.logging.mapper;

import com.stubee.persistence.common.mapper.DomainEntityMapper;
import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.persistence.common.annotations.Mapper;
import com.stubee.persistence.domain.logging.entity.LoggingEntity;

@Mapper
public class LoggingMapper implements DomainEntityMapper<LoggingEntity, Logging> {

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