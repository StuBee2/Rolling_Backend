package com.stubee.rollingdomains.domain.company.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.company.consts.CompanyStatus;
import lombok.Builder;

import java.time.LocalDateTime;

public record CompanyDetails(
        String name,
        Address companyAddress,
        String description,
        String imgUrl,
        CompanyStatus companyStatus,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    @Builder(builderClassName = "ExceptDateBuilder", builderMethodName = "ExceptDateBuilder")
    public CompanyDetails(String name, Address companyAddress, String description, String imgUrl) {
        this(name, companyAddress, description, imgUrl, CompanyStatus.ACCEPTED, null, null);
    }

    @Builder(builderClassName = "WithDateBuilder", builderMethodName = "WithDateBuilder")
    public CompanyDetails {
        Assert.notNull(name, "CompanyName must not be null");
        Assert.notNull(companyAddress, "CompanyAddress must not be null");
        Assert.notNull(description, "Description must not be null");
        Assert.notNull(companyStatus, "CompanyStatus must not be null");
    }

    public CompanyDetails updateStatus(final CompanyStatus companyStatus) {
        return new CompanyDetails(name, companyAddress, description, imgUrl, companyStatus, createdAt, null);
    }

}