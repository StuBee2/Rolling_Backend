package com.stubee.rollingcore.domain.company.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CompanyDetails(
        String name,
        Address companyAddress,
        String description,
        String imgUrl,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    public static CompanyDetails create(final String name, final String address, final String description, final String imgUrl) {
        return CompanyDetails.builder()
                .name(name)
                .companyAddress(Address.create(address))
                .description(description)
                .imgUrl(imgUrl)
                .build();
    }

    public static CompanyDetails createWithDate(final String name, final String address, final String description, final String imgUrl,
                                        final LocalDateTime createdAt, final LocalDateTime modifiedAt) {
        return CompanyDetails.builder()
                .name(name)
                .companyAddress(Address.create(address))
                .description(description)
                .imgUrl(imgUrl)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }

}