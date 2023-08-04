package com.stubee.rollingadapter.persistence.employment.mapper;

import com.stubee.rollingadapter.common.annotation.Mapper;
import com.stubee.rollingadapter.common.mapper.GenericMapper;
import com.stubee.rollingadapter.persistence.employment.entity.EmploymentJpaEntity;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import com.stubee.rollingcore.domain.employment.model.Employment;
import com.stubee.rollingcore.domain.employment.model.EmploymentDetails;
import com.stubee.rollingcore.domain.employment.model.EmploymentId;
import com.stubee.rollingcore.domain.member.model.MemberId;

@Mapper
public class EmploymentMapper implements GenericMapper<EmploymentJpaEntity, Employment> {

    @Override
    public EmploymentJpaEntity toEntity(final Employment domain) {
        return EmploymentJpaEntity.builder()
                .employeeId(domain.employeeId().id())
                .employerId(domain.employerId().id())
                .employmentStatus(domain.employmentDetails().employmentStatus())
                .build();
    }

    @Override
    public Employment toDomain(final EmploymentJpaEntity entity) {
        return Employment.createWithEmploymentId(EmploymentId.create(entity.getId()),
                MemberId.create(entity.getEmployeeId()), CompanyId.create(entity.getEmployerId()),
                employmentDetails(entity));
    }

    private EmploymentDetails employmentDetails(final EmploymentJpaEntity entity) {
        return EmploymentDetails.createWithDate(entity.getEmploymentStatus(), entity.getCreatedAt());
    }

}