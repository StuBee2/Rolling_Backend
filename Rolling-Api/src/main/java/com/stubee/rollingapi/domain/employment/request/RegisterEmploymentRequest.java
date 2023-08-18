package com.stubee.rollingapi.domain.employment.request;

import com.stubee.employmentapplication.commands.RegisterEmploymentCommand;
import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RegisterEmploymentRequest(
        @NotNull UUID employerId,
        @NotNull EmploymentStatus employmentStatus) {
    public RegisterEmploymentCommand toCommand() {
        return RegisterEmploymentCommand.create(employerId, employmentStatus);
    }
}