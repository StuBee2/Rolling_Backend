package com.stubee.rollingdomains.domain.company.services.commands;

import com.stubee.rollingdomains.common.model.Grades;
import com.stubee.rollingdomains.domain.company.model.Address;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.CompanyDetails;
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
        return Company.ExceptIdBuilder()
                .companyDetails(CompanyDetails.ExceptDateBuilder()
                        .name(name)
                        .companyAddress(Address.of(address))
                        .description(description)
                        .imgUrl(imgUrl)
                        .build())
                .companyGrades(Grades.zeroGrades())
                .registrantId(RegistrantId.of(memberId))
                .build();
    }
}