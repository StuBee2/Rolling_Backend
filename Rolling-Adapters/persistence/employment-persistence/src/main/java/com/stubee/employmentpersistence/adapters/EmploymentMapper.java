package com.stubee.employmentpersistence.adapters;

import com.stubee.persistencecommons.annotations.DomainObjectMapper;
import com.stubee.persistencecommons.entity.EmploymentEntity;
import com.stubee.rollingdomains.domain.employment.model.*;

@DomainObjectMapper
class EmploymentMapper implements com.stubee.persistencecommons.mapper.DomainObjectMapper<EmploymentEntity, Employment> {

    @Override
    public EmploymentEntity toEntity(final Employment domain) {
        return EmploymentEntity.builder()
                .employeeId(domain.employmentDetails().employeeId().getId())
                .employerId(domain.employmentDetails().employerId().getId())
                .employmentStatus(domain.employmentDetails().employmentStatus())
                .build();
    }

    @Override
    public Employment toDomain(final EmploymentEntity entity) {
        return Employment.WithIdBuilder()
                .employmentId(EmploymentId.of(entity.getId()))
                .employmentDetails(employmentDetails(entity))
                .build();
    }

    private EmploymentDetails employmentDetails(final EmploymentEntity entity) {
        return EmploymentDetails.WithDateBuilder()
                .employeeId(EmployeeId.of(entity.getEmployeeId()))
                .employerId(EmployerId.of(entity.getEmployerId()))
                .employmentStatus(entity.getEmploymentStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}