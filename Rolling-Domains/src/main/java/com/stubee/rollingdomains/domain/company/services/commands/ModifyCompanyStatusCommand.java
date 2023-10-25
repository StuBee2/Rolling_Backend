package com.stubee.rollingdomains.domain.company.services.commands;

public record ModifyCompanyStatusCommand(
        Long companyId,
        boolean status) {
    public static ModifyCompanyStatusCommand accept(final Long companyId) {
        return new ModifyCompanyStatusCommand(companyId, true);
    }

    public static ModifyCompanyStatusCommand deny(final Long companyId) {
        return new ModifyCompanyStatusCommand(companyId, false);
    }
}