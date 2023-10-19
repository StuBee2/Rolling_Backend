package com.stubee.loggingpersistence.mapper;

import com.stubee.persistencecommons.annotations.DomainObjectMapper;
import com.stubee.persistencecommons.entity.HistoryLoggingEntity;
import com.stubee.rollingdomains.domain.logging.model.HistoryLogging;
import com.stubee.rollingdomains.domain.member.model.MemberId;

@DomainObjectMapper
public class HistoryLoggingMapper implements com.stubee.persistencecommons.mapper.DomainObjectMapper<HistoryLoggingEntity, HistoryLogging> {

    @Override
    public HistoryLoggingEntity toEntity(final HistoryLogging domain) {
        return HistoryLoggingEntity.builder()
                .description(domain.description())
                .module(domain.module())
                .memberId(domain.memberId().getId())
                .isAnonymous(domain.isAnonymous())
                .build();
    }

    @Override
    public HistoryLogging toDomain(final HistoryLoggingEntity entity) {
        return HistoryLogging.WithIdBuilder()
                .id(entity.getId())
                .description(entity.getDescription())
                .module(entity.getModule())
                .memberId(MemberId.of(entity.getMemberId()))
                .isAnonymous(entity.getIsAnonymous())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}