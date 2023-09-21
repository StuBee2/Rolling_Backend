package com.stubee.rollingdomains.domain.employment.services.commands;

import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;
import com.stubee.rollingdomains.domain.employment.model.EmployeeId;
import com.stubee.rollingdomains.domain.employment.model.EmployerId;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import com.stubee.rollingdomains.domain.employment.model.EmploymentDetails;
import com.stubee.rollingdomains.domain.member.model.MemberId;

import java.util.UUID;

public record RegisterEmploymentCommand(
        UUID employerId,
        EmploymentStatus employmentStatus) {
    public static RegisterEmploymentCommand create(final UUID employerId, final EmploymentStatus employmentStatus) {
        return new RegisterEmploymentCommand(employerId, employmentStatus);
    }

    public Employment toDomain(final MemberId employeeId) {
        return Employment.createExceptEmploymentId(EmployeeId.of(employeeId),
                EmployerId.of(employerId), EmploymentDetails.create(employmentStatus));
    }
}