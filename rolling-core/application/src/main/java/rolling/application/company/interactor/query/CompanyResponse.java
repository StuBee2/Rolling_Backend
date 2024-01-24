package rolling.application.company.interactor.query;

import rolling.domain.common.model.TSID;
import rolling.domain.company.consts.CompanyStatus;
import rolling.domain.company.model.Company;
import rolling.domain.company.model.CompanyDetails;
import rolling.domain.company.model.CompanyGrades;

import java.time.LocalDateTime;

public record CompanyResponse(
        TSID companyId,
        CompanyStatus status,
        TSID registrantId,
        CompanyDetails companyDetails,
        CompanyGrades companyGrades,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    public static CompanyResponse of(final Company company) {
        return new CompanyResponse(
                TSID.of(company.id()),
                company.status(),
                TSID.of(company.registrantId()),
                company.details(),
                company.grades(),
                company.createdAt(),
                company.modifiedAt()
        );
    }
}