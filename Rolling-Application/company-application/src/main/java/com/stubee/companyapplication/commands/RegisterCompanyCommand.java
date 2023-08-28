package com.stubee.companyapplication.commands;

import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.RegistrantId;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public record RegisterCompanyCommand(
        String name,
        String address,
        String description,
        String imgUrl) {
    public static RegisterCompanyCommand create(final String name, final String address,
                                                final String description, final String imgUrl) {
        return new RegisterCompanyCommand(name, address, description, imgUrl);
    }

    public Company toDomain(final MemberId memberId) {
        return Company.create(name, address, description, imgUrl, RegistrantId.create(memberId.getId()));
    }
}