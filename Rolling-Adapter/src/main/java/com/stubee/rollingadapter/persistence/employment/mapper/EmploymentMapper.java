package com.stubee.rollingadapter.persistence.employment.mapper;

import com.stubee.rollingadapter.common.annotation.Mapper;
import com.stubee.rollingadapter.common.mapper.GenericMapper;
import com.stubee.rollingadapter.persistence.employment.entity.EmploymentEntity;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import com.stubee.rollingcore.domain.employment.model.Employment;
import com.stubee.rollingcore.domain.employment.model.EmploymentDetails;
import com.stubee.rollingcore.domain.employment.model.EmploymentId;
import com.stubee.rollingcore.domain.member.model.MemberId;

@Mapper
public class EmploymentMapper implements GenericMapper<EmploymentEntity, Employment> {

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