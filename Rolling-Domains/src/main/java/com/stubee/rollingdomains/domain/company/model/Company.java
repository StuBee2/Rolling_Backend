package com.stubee.rollingdomains.domain.company.model;

import com.stubee.rollingdomains.common.model.Grades;
import com.stubee.rollingdomains.domain.company.consts.CompanyStatus;
import lombok.Builder;

import java.util.Objects;

public record Company (
        CompanyId companyId,
        CompanyDetails companyDetails,
        Grades companyGrades,
        RegistrantId registrantId) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Company(CompanyDetails companyDetails, Grades companyGrades, RegistrantId registrantId) {
        this(null, companyDetails, companyGrades, registrantId);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Company {
        Objects.requireNonNull(companyDetails, "CompanyDetails can not be null");
        Objects.requireNonNull(companyGrades, "CompanyGrades can not be null");
        Objects.requireNonNull(registrantId, "RegistrantId can not be null");
    }

    public Company updateGrades(final Grades companyGrades) {
        return new Company(companyId, companyDetails, companyGrades, registrantId);
    }

    public Company updateStatus(final boolean isAccepted) {
        return new Company(companyId, companyDetails.updateStatus(CompanyStatus.from(isAccepted)), companyGrades, registrantId);
    }
}