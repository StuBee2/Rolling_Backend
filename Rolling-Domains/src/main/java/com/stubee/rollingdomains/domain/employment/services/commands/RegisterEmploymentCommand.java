package com.stubee.rollingdomains.domain.employment.services.commands;

import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;
import com.stubee.rollingdomains.domain.employment.model.EmployeeId;
import com.stubee.rollingdomains.domain.employment.model.EmployerId;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import com.stubee.rollingdomains.domain.employment.model.EmploymentDetails;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public record RegisterEmploymentCommand(
        Long employerId,
        EmploymentStatus employmentStatus) {
    public static RegisterEmploymentCommand create(final Long employerId, final EmploymentStatus employmentStatus) {
        return new RegisterEmploymentCommand(employerId, employmentStatus);
    }

    public Employment toDomain(final MemberId employeeId) {
        return Employment.ExceptIdBuilder()
                .employeeId(EmployeeId.of(employeeId))
                .employerId(EmployerId.of(employerId))
                .employmentDetails(EmploymentDetails.ExceptDateBuilder()
                        .employmentStatus(employmentStatus)
                        .build())
                .build();
    }
}