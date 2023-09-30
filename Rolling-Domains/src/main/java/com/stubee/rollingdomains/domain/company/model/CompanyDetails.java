package com.stubee.rollingdomains.domain.company.model;

import com.stubee.rollingdomains.domain.company.consts.CompanyStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

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
        Objects.requireNonNull(name);
        Objects.requireNonNull(companyAddress);
        Objects.requireNonNull(description);
        Objects.requireNonNull(companyStatus);
    }

    public CompanyDetails updateStatus(final CompanyStatus companyStatus) {
        return new CompanyDetails(name, companyAddress, description, imgUrl, companyStatus, createdAt, null);
    }

}