package com.stubee.rollingdomains.domain.company.services.commands;

import com.stubee.rollingdomains.domain.company.model.CompanyId;

public record DeleteCompanyCommand(
        CompanyId companyId) {
    public static DeleteCompanyCommand toCommand(final Long companyId) {
        return new DeleteCompanyCommand(CompanyId.of(companyId));
    }
}