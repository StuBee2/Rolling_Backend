package com.stubee.persistence.domain.employment.mapper;

import com.stubee.persistence.common.mapper.DomainEntityMapper;
import com.stubee.rollingdomains.domain.employment.model.*;
import com.stubee.persistence.common.annotations.Mapper;
import com.stubee.persistence.domain.employment.entity.EmploymentEntity;

@Mapper
public class EmploymentMapper implements DomainEntityMapper<EmploymentEntity, Employment> {

    @Override
    public EmploymentEntity toEntity(final Employment domain) {
        return EmploymentEntity.builder()
                .employeeId(domain.employeeId().getId())
                .employerId(domain.employerId().getId())
                .employmentStatus(domain.employmentDetails().employmentStatus())
                .build();
    }

    @Override
    public Employment toDomain(final EmploymentEntity entity) {
        return Employment.createWithEmploymentId(EmploymentId.create(entity.getId()),
                EmployeeId.create(entity.getEmployeeId()), EmployerId.create(entity.getEmployerId()),
                employmentDetails(entity));
    }

    private EmploymentDetails employmentDetails(final EmploymentEntity entity) {
        return EmploymentDetails.createWithDate(entity.getEmploymentStatus(), entity.getCreatedAt());
    }

}