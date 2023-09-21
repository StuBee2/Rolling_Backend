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
        return Employment.createWithEmploymentId(EmploymentId.of(entity.getId()),
                EmployeeId.of(entity.getEmployeeId()), EmployerId.of(entity.getEmployerId()),
                employmentDetails(entity));
    }

    private EmploymentDetails employmentDetails(final EmploymentEntity entity) {
        return EmploymentDetails.createWithDate(entity.getEmploymentStatus(), entity.getCreatedAt());
    }

}