package com.stubee.rollingapi.domain.employment;

import com.stubee.employmentapplication.usecases.command.RegisterEmploymentCommand;
import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;
import jakarta.validation.constraints.NotNull;

record RegisterEmploymentRequest(
        @NotNull Long employerId,
        @NotNull EmploymentStatus employmentStatus) {
    public RegisterEmploymentCommand toCommand() {
        return RegisterEmploymentCommand.create(employerId, employmentStatus);
    }
}