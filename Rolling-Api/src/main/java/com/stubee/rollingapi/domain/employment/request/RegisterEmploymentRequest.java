package com.stubee.rollingapi.domain.employment.request;

import com.stubee.employmentapplication.usecases.command.RegisterEmploymentCommand;
import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;
import jakarta.validation.constraints.NotNull;

public record RegisterEmploymentRequest(
        @NotNull Long employerId,
        @NotNull EmploymentStatus employmentStatus) {
    public RegisterEmploymentCommand toCommand() {
        return RegisterEmploymentCommand.create(employerId, employmentStatus);
    }
}