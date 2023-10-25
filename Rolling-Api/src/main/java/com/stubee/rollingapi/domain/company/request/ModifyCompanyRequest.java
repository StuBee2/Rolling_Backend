package com.stubee.rollingapi.domain.company.request;

import com.stubee.rollingdomains.domain.company.services.commands.ModifyCompanyDetailsCommand;

public record ModifyCompanyRequest(
        String description,
        String companyAddress,
        String url,
        Integer rgb) {
    public ModifyCompanyDetailsCommand toCommand(Long id) {
        return new ModifyCompanyDetailsCommand(id, description, companyAddress, url, rgb);
    }
}