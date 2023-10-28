package com.stubee.rollingdomains.domain.company.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.company.consts.CompanyStatus;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.Builder;

public record Company (
        CompanyId companyId,
        CompanyDetails companyDetails,
        CompanyGrades companyGrades) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Company(CompanyDetails companyDetails, CompanyGrades companyGrades) {
        this(null, companyDetails, companyGrades);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Company {
        Assert.notNull(companyDetails, "CompanyDetails must not be null");
        Assert.notNull(companyGrades, "CompanyGrades must not be null");
    }

    public void isAuthor(final MemberId memberId) {
        companyDetails.registrantId().isEqual(memberId);
    }

    public Company update(final String name, final String description, final Address companyAddress, final CompanyLogo companyLogo) {
        return new Company(companyId, companyDetails.update(name, description, companyAddress, companyLogo), companyGrades);
    }

    public Company update(final CompanyGrades companyGrades) {
        return new Company(companyId, companyDetails, companyGrades);
    }

    public Company update(final boolean status) {
        return new Company(companyId, companyDetails.update(CompanyStatus.from(status)), companyGrades);
    }
}