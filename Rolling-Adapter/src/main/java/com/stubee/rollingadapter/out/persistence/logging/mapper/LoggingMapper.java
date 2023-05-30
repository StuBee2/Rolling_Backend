package com.stubee.rollingadapter.out.persistence.logging.mapper;

import com.stubee.rollingadapter.out.common.mapper.GenericMapper;
import com.stubee.rollingadapter.out.persistence.logging.entity.LoggingEntity;
import com.stubee.rollingcore.domain.logging.model.Logging;
import org.springframework.stereotype.Component;

@Component
public class LoggingMapper implements GenericMapper<LoggingEntity, Logging> {

    @Override
    public LoggingEntity toEntity(final Logging domain) {
        return LoggingEntity.builder()
                .description(domain.description())
                .module(domain.module())
                .memberId(domain.memberId())
                .build();
    }

    @Override
    public Logging toDomain(final LoggingEntity entity) {
        return Logging.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .module(entity.getModule())
                .memberId(entity.getMemberId())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}