package com.stubee.rollingdomains.domain.company.services.commands;

import com.stubee.rollingdomains.domain.company.model.*;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public record RegisterCompanyCommand(
        String name,
        String address,
        String description,
        String imgUrl,
        Integer rgb) {
    public static RegisterCompanyCommand create(final String name, final String address,
                                                final String description, final String imgUrl, final Integer rgb) {
        return new RegisterCompanyCommand(name, address, description, imgUrl, rgb);
    }

    public Company toDomain(final MemberId memberId) {
        return Company.ExceptIdBuilder()
                .companyDetails(CompanyDetails.ExceptDateBuilder()
                        .registrantId(RegistrantId.of(memberId))
                        .name(name)
                        .description(description)
                        .companyAddress(Address.of(address))
                        .companyLogo(CompanyLogo.of(imgUrl, rgb))
                        .build())
                .companyGrades(CompanyGrades.zero())
                .build();
    }
}