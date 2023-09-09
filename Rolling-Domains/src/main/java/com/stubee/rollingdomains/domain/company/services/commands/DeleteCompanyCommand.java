package com.stubee.rollingdomains.domain.company.services.commands;

import com.stubee.rollingdomains.domain.company.model.CompanyId;

import java.util.UUID;

public record DeleteCompanyCommand(
        CompanyId companyId) {
    public static DeleteCompanyCommand toCommand(final UUID companyId) {
        return new DeleteCompanyCommand(CompanyId.create(companyId));
    }
}