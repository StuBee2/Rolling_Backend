package com.stubee.employmentapplication.mapper;

import com.stubee.rollingdomains.domain.employment.model.EmployeeId;
import com.stubee.rollingdomains.domain.employment.model.EmployerId;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import com.stubee.rollingdomains.domain.employment.model.EmploymentDetails;
import com.stubee.employmentapplication.usecases.command.RegisterEmploymentCommand;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public abstract class EmploymentMapper {

    public static Employment toDomain(final RegisterEmploymentCommand command, final MemberId employeeId) {
        return Employment.ExceptIdBuilder()
                .employmentDetails(EmploymentDetails.ExceptDateBuilder()
                        .employeeId(EmployeeId.of(employeeId))
                        .employerId(EmployerId.of(command.employerId()))
                        .employmentStatus(command.employmentStatus())
                        .build())
                .build();
    }

}