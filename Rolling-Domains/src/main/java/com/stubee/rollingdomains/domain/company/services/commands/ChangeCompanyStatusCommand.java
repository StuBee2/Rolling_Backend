package com.stubee.rollingdomains.domain.company.services.commands;

public record ChangeCompanyStatusCommand(
        Long companyId,
        boolean status) {
    public static ChangeCompanyStatusCommand accept(final Long companyId) {
        return new ChangeCompanyStatusCommand(companyId, true);
    }

    public static ChangeCompanyStatusCommand deny(final Long companyId) {
        return new ChangeCompanyStatusCommand(companyId, false);
    }
}