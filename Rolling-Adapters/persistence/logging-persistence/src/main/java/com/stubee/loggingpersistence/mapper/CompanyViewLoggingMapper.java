package com.stubee.loggingpersistence.mapper;

import com.stubee.persistencecommons.annotations.DomainObjectMapper;
import com.stubee.persistencecommons.entity.CompanyViewLoggingEntity;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.logging.model.CompanyViewLogging;
import com.stubee.rollingdomains.domain.member.model.MemberId;

@DomainObjectMapper
public class CompanyViewLoggingMapper implements com.stubee.persistencecommons.mapper.DomainObjectMapper<CompanyViewLoggingEntity, CompanyViewLogging> {

    @Override
    public CompanyViewLoggingEntity toEntity(CompanyViewLogging domain) {
        return CompanyViewLoggingEntity.builder()
                .memberId(domain.memberId().getId())
                .companyId(domain.companyId().getId())
                .isAnonymous(domain.isAnonymous())
                .build();
    }

    @Override
    public CompanyViewLogging toDomain(CompanyViewLoggingEntity entity) {
        return CompanyViewLogging.WithIdBuilder()
                .id(entity.getId())
                .memberId(MemberId.of(entity.getMemberId()))
                .companyId(CompanyId.of(entity.getCompanyId()))
                .isAnonymous(entity.getIsAnonymous())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}