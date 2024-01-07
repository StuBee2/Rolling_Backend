package rolling.application.company.interactor.query;

import rolling.domain.common.model.TSID;
import rolling.domain.company.model.Company;
import rolling.domain.company.model.CompanyDetails;
import rolling.domain.company.model.CompanyGrades;

public record CompanyResponse(TSID companyId, CompanyDetails companyDetails, CompanyGrades companyGrades,
                              TSID registrantId) {
    public static CompanyResponse of(final Company company) {
        return new CompanyResponse(TSID.of(company.companyId()), company.companyDetails(),
                company.companyGrades(), TSID.of(company.companyDetails().registrantId()));
    }
}