package com.stubee.companyapplication.usecases.query;

import com.stubee.applicationcommons.model.response.TSID;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.CompanyDetails;
import com.stubee.rollingdomains.domain.company.model.CompanyGrades;

public record CompanyResponse(
        TSID companyId,
        CompanyDetails companyDetails,
        CompanyGrades companyGrades,
        TSID registrantId
) {
    public static CompanyResponse of(Company company) {
        return new CompanyResponse(TSID.of(company.companyId()), company.companyDetails(),
                company.companyGrades(), TSID.of(company.companyDetails().registrantId()));
    }
}