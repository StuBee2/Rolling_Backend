package com.stubee.employmentpersistence.mapper;

import com.stubee.persistencecommons.annotations.DomainObjectMapper;
import com.stubee.persistencecommons.entity.EmploymentEntity;
import com.stubee.rollingdomains.domain.employment.model.*;

@DomainObjectMapper
public class EmploymentMapper implements com.stubee.persistencecommons.mapper.DomainObjectMapper<EmploymentEntity, Employment> {

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
        return Employment.WithIdBuilder()
                .employmentId(EmploymentId.of(entity.getId()))
                .employeeId(EmployeeId.of(entity.getEmployeeId()))
                .employerId(EmployerId.of(entity.getEmployerId()))
                .employmentDetails(EmploymentDetails.WithDateBuilder()
                        .employmentStatus(entity.getEmploymentStatus())
                        .createdAt(entity.getCreatedAt())
                        .build())
                .build();
    }

}