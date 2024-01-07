package rolling.domain.company.model;

import lombok.Builder;
import rolling.domain.common.error.Assert;
import rolling.domain.company.consts.CompanyStatus;
import rolling.domain.company.exception.DuplicatedCompanyNameException;
import rolling.domain.company.service.CompanyService;
import rolling.domain.member.model.MemberId;

import java.util.Objects;

public final class Company {

    private final CompanyId companyId;
    private CompanyDetails companyDetails;
    private CompanyGrades companyGrades;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Company(CompanyDetails companyDetails, CompanyGrades companyGrades) {
        this(null, companyDetails, companyGrades);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Company(CompanyId companyId, CompanyDetails companyDetails, CompanyGrades companyGrades) {
        Assert.notNull(companyDetails, "CompanyDetails must not be null");
        Assert.notNull(companyGrades, "CompanyGrades must not be null");
        this.companyId = companyId;
        this.companyDetails = companyDetails;
        this.companyGrades = companyGrades;
    }

    public void modify(final CompanyDetails companyDetails, final CompanyService companyService) {
        isAuthor(companyDetails.registrantId());

        if(!Objects.equals(this.companyDetails.name(), companyDetails.name())) {
            if(companyService.isNameDuplicate(companyDetails.name())) {
                throw DuplicatedCompanyNameException.EXCEPTION;
            }
        }

        this.companyDetails = this.companyDetails.cover(companyDetails);
    }

    public void modify(final CompanyStatus status) {
        this.companyDetails = new CompanyDetails(companyDetails.registrantId(), companyDetails.name(), companyDetails.description(),
                companyDetails.companyAddress(), companyDetails.companyLogo(), status, companyDetails.createdAt(), companyDetails.modifiedAt());
    }

    public void modify(final CompanyGrades companyGrades) {
        this.companyGrades = companyGrades;
    }

    public void isAuthor(final MemberId memberId) {
        companyDetails.registrantId().isEqual(memberId);
    }

    public CompanyId companyId() {
        return companyId;
    }

    public CompanyDetails companyDetails() {
        return companyDetails;
    }

    public CompanyGrades companyGrades() {
        return companyGrades;
    }

}