package rolling.application.company.interactor.command;

import rolling.domain.company.model.CompanyId;

public record DeleteCompanyCommand(
        CompanyId companyId) {
    public static DeleteCompanyCommand toCommand(final Long companyId) {
        return new DeleteCompanyCommand(CompanyId.of(companyId));
    }
}