package com.stubee.employmentpersistence.mapper;

import com.stubee.persistencecommons.commons.annotations.Mapper;
import com.stubee.persistencecommons.commons.entity.EmploymentEntity;
import com.stubee.persistencecommons.commons.mapper.DomainObjectMapper;
import com.stubee.rollingdomains.domain.employment.model.*;

@Mapper
public class EmploymentMapper implements DomainObjectMapper<EmploymentEntity, Employment> {

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