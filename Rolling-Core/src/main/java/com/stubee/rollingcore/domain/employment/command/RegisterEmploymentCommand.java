package com.stubee.rollingcore.domain.employment.command;

import com.stubee.rollingcore.domain.employment.enums.EmploymentStatus;

import java.util.UUID;

public record RegisterEmploymentCommand(
        UUID employerId,
        EmploymentStatus employmentStatus) {
    public static RegisterEmploymentCommand create(final UUID employerId, final EmploymentStatus employmentStatus) {
        return new RegisterEmploymentCommand(employerId, employmentStatus);
    }
}