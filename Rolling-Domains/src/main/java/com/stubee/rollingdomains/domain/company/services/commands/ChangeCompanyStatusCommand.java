package com.stubee.rollingdomains.domain.company.services.commands;

import java.util.UUID;

public record ChangeCompanyStatusCommand(
        UUID companyId,
        boolean status) {
    public static ChangeCompanyStatusCommand accept(final UUID companyId) {
        return new ChangeCompanyStatusCommand(companyId, true);
    }

    public static ChangeCompanyStatusCommand deny(final UUID companyId) {
        return new ChangeCompanyStatusCommand(companyId, false);
    }
}