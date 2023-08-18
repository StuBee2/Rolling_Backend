package com.stubee.employmentapplication.commands;

import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;

import java.util.UUID;

public record RegisterEmploymentCommand(
        UUID employerId,
        EmploymentStatus employmentStatus) {
    public static RegisterEmploymentCommand create(final UUID employerId, final EmploymentStatus employmentStatus) {
        return new RegisterEmploymentCommand(employerId, employmentStatus);
    }
}