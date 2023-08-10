package com.stubee.rollingexternal.persistence.domain.employment.mapper;

import com.stubee.rollingcommons.commons.annotations.Mapper;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import com.stubee.rollingdomains.domain.employment.model.EmploymentDetails;
import com.stubee.rollingdomains.domain.employment.model.EmploymentId;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingexternal.persistence.common.mapper.DomainEntityMapper;
import com.stubee.rollingexternal.persistence.domain.employment.entity.EmploymentEntity;

@Mapper
public class EmploymentMapper implements DomainEntityMapper<EmploymentEntity, Employment> {

    @Override
    public EmploymentEntity toEntity(final Employment domain) {
        return EmploymentEntity.builder()
                .employeeId(domain.employeeId().id())
                .employerId(domain.employerId().id())
                .employmentStatus(domain.employmentDetails().employmentStatus())
                .build();
    }

    @Override
    public Employment toDomain(final EmploymentEntity entity) {
        return Employment.createWithEmploymentId(EmploymentId.create(entity.getId()),
                MemberId.create(entity.getEmployeeId()), CompanyId.create(entity.getEmployerId()),
                employmentDetails(entity));
    }

    private EmploymentDetails employmentDetails(final EmploymentEntity entity) {
        return EmploymentDetails.createWithDate(entity.getEmploymentStatus(), entity.getCreatedAt());
    }

}