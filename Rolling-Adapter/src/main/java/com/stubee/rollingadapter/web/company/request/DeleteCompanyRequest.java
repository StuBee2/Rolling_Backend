package com.stubee.rollingadapter.web.company.request;

import com.stubee.rollingcore.domain.company.model.CompanyId;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DeleteCompanyRequest(
        @NotBlank UUID companyId) {
    public CompanyId toCompanyId() {
        return CompanyId.create(companyId);
    }
}