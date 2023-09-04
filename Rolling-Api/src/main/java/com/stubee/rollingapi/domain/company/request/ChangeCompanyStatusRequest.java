package com.stubee.rollingapi.domain.company.request;

import com.stubee.companyapplication.commands.ChangeCompanyStatusCommand;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ChangeCompanyStatusRequest(
        @NotNull UUID companyId,
        boolean isAccepted) {
    public ChangeCompanyStatusCommand toCommand() {
        return new ChangeCompanyStatusCommand(companyId, isAccepted);
    }
}