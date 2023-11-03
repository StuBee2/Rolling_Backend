package com.stubee.employmentapplication.usecases.command;

import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;

public record RegisterEmploymentCommand(
        Long employerId,
        EmploymentStatus employmentStatus) {
    public static RegisterEmploymentCommand create(final Long employerId, final EmploymentStatus employmentStatus) {
        return new RegisterEmploymentCommand(employerId, employmentStatus);
    }
}