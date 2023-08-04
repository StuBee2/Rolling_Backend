package com.stubee.rollingadapter.web.employment.request;

import com.stubee.rollingcore.domain.employment.command.RegisterEmploymentCommand;
import com.stubee.rollingcore.domain.employment.enums.EmploymentStatus;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RegisterEmploymentRequest(
        @NotNull UUID employerId,
        @NotNull EmploymentStatus employmentStatus) {
    public RegisterEmploymentCommand toCommand() {
        return RegisterEmploymentCommand.create(employerId, employmentStatus);
    }
}