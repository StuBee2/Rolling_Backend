package com.stubee.rollingadapter.web.employment.request;

import com.stubee.rollingcore.domain.employment.command.RegisterEmploymentCommand;
import com.stubee.rollingcore.domain.employment.enums.EmploymentStatus;

import java.util.UUID;

public record RegisterEmploymentRequest(
        UUID employerId,
        EmploymentStatus employmentStatus) {
    public RegisterEmploymentCommand toCommand() {
        return RegisterEmploymentCommand.create(employerId, employmentStatus);
    }
}