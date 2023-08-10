package com.stubee.rollingapi.domain.employment.request;

import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;
import com.stubee.rollingusecases.domain.employment.commands.RegisterEmploymentCommand;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RegisterEmploymentRequest(
        @NotNull UUID employerId,
        @NotNull EmploymentStatus employmentStatus) {
    public RegisterEmploymentCommand toCommand() {
        return RegisterEmploymentCommand.create(employerId, employmentStatus);
    }
}