package com.stubee.companyapplication.usecases.command;

import com.stubee.rollingdomains.domain.company.consts.CompanyStatus;
import com.stubee.rollingdomains.domain.company.model.CompanyId;

public record ModifyCompanyStatusCommand(
        CompanyId companyId,
        CompanyStatus status) {
    public static ModifyCompanyStatusCommand accept(final Long companyId) {
        return new ModifyCompanyStatusCommand(CompanyId.of(companyId), CompanyStatus.ACCEPTED);
    }

    public static ModifyCompanyStatusCommand deny(final Long companyId) {
        return new ModifyCompanyStatusCommand(CompanyId.of(companyId), CompanyStatus.DECLINED);
    }
}