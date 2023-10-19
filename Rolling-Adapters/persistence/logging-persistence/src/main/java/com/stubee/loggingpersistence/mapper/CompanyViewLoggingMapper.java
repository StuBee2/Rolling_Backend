package com.stubee.loggingpersistence.mapper;

import com.stubee.persistencecommons.annotations.DomainObjectMapper;
import com.stubee.persistencecommons.entity.CompanyViewLoggingEntity;
import com.stubee.rollingdomains.domain.logging.model.CompanyViewLogging;

@DomainObjectMapper
public class CompanyViewLoggingMapper implements com.stubee.persistencecommons.mapper.DomainObjectMapper<CompanyViewLoggingEntity, CompanyViewLogging> {

    @Override
    public CompanyViewLoggingEntity toEntity(CompanyViewLogging domain) {
        return CompanyViewLoggingEntity.builder()
                .memberId(domain.memberId())
                .companyId(domain.companyId())
                .isAnonymous(domain.isAnonymous())
                .build();
    }

    @Override
    public CompanyViewLogging toDomain(CompanyViewLoggingEntity entity) {
        return CompanyViewLogging.WithIdBuilder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .companyId(entity.getCompanyId())
                .isAnonymous(entity.getIsAnonymous())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}