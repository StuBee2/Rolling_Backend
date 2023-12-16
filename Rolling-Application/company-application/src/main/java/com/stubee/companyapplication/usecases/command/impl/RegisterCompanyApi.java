package com.stubee.companyapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.rollingdomains.common.model.TSID;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.services.RegisterCompanyService;
import com.stubee.companyapplication.usecases.command.RegisterCompanyCommand;
import com.stubee.companyapplication.usecases.command.RegisterCompanyUseCase;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
class RegisterCompanyApi implements RegisterCompanyUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;
    private final RegisterCompanyService registerCompanyService;

    @Override
    public TSID register(final RegisterCompanyCommand command) {
        final MemberId memberId = getCurrentMemberPort.getMemberId();

        final Company company = CompanyMapper.toDomain(command, memberId);

        return TSID.of(registerCompanyService.register(company).companyId());
    }

}