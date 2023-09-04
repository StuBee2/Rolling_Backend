package com.stubee.rollingdomains.domain.company.model;

import com.stubee.rollingdomains.domain.company.consts.CompanyStatus;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record CompanyDetails(
        String name,
        Address companyAddress,
        String description,
        String imgUrl,
        CompanyStatus companyStatus,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    public static CompanyDetails create(final String name, final String address, final String description, final String imgUrl) {
        return CompanyDetails.builder()
                .name(name)
                .companyAddress(Address.create(address))
                .description(description)
                .imgUrl(imgUrl)
                .companyStatus(CompanyStatus.PENDING)
                .build();
    }

    public static CompanyDetails createWithDate(final String name, final String address, final String description, final String imgUrl,
                                        final CompanyStatus companyStatus, final LocalDateTime createdAt, final LocalDateTime modifiedAt) {
        return CompanyDetails.builder()
                .name(name)
                .companyAddress(Address.create(address))
                .description(description)
                .imgUrl(imgUrl)
                .companyStatus(companyStatus)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }

    public CompanyDetails updateStatus(final CompanyStatus companyStatus) {
        return CompanyDetails.builder()
                .name(name)
                .companyAddress(companyAddress)
                .description(description)
                .imgUrl(imgUrl)
                .companyStatus(companyStatus)
                .createdAt(createdAt)
                .build();
    }

}