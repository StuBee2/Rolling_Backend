package com.stubee.loggingpersistence.mapper;

import com.stubee.persistencecommons.annotations.DomainObjectMapper;
import com.stubee.persistencecommons.entity.LoggingEntity;
import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.rollingdomains.domain.member.model.MemberId;

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
        return Logging.WithIdBuilder()
                .id(entity.getId())
                .description(entity.getDescription())
                .module(entity.getModule())
                .memberId(MemberId.of(entity.getMemberId()))
                .createdAt(entity.getCreatedAt())
                .build();
    }

}