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
        Objects.requireNonNull(name, "CompanyName can not be null");
        Objects.requireNonNull(companyAddress, "CompanyAddress can not be null");
        Objects.requireNonNull(description, "Description can not be null");
        Objects.requireNonNull(companyStatus, "CompanyStatus can not be null");
    }

    public CompanyDetails updateStatus(final CompanyStatus companyStatus) {
        return new CompanyDetails(name, companyAddress, description, imgUrl, companyStatus, createdAt, null);
    }

}