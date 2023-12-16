package com.stubee.companyapplication.usecases.command.impl;

import com.stubee.rollingdomains.domain.company.model.*;
import com.stubee.companyapplication.usecases.command.RegisterCompanyCommand;
import com.stubee.companyapplication.usecases.command.ModifyCompanyDetailsCommand;
import com.stubee.rollingdomains.domain.member.model.MemberId;

abstract class CompanyMapper {

    public static Company toDomain(final RegisterCompanyCommand command, final MemberId memberId) {
        return Company.ExceptIdBuilder()
                .companyDetails(CompanyDetails.ExceptDateBuilder()
                        .registrantId(RegistrantId.of(memberId))
                        .name(command.name())
                        .description(command.description())
                        .companyAddress(Address.of(command.address(), command.addressEtc()))
                        .companyLogo(CompanyLogo.of(command.imgUrl(), command.rgb()))
                        .build())
                .companyGrades(CompanyGrades.zero())
                .build();
    }

    public static CompanyDetails toDetails(final ModifyCompanyDetailsCommand command, final MemberId memberId) {
        return CompanyDetails.ExceptDateBuilder()
                .registrantId(RegistrantId.of(memberId))
                .name(command.name())
                .description(command.description())
                .companyAddress(Address.of(command.companyAddress(), command.companyAddressEtc()))
                .companyLogo(CompanyLogo.of(command.url(), command.rgb()))
                .build();
    }

}