package com.stubee.companyapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.rollingdomains.domain.company.services.RegisterCompanyService;
import com.stubee.rollingdomains.domain.company.services.commands.RegisterCompanyCommand;
import com.stubee.companyapplication.usecases.command.RegisterCompanyUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.member.services.GetMemberInfoService;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class RegisterCompanyApi implements RegisterCompanyUseCase {

    private final GetMemberInfoService getMemberInfoService;
    private final RegisterCompanyService registerCompanyService;

    @Override
    public Company register(final RegisterCompanyCommand command) {
        final MemberId memberId = getMemberInfoService.getMemberId();

        return registerCompanyService.register(command, memberId);
    }

}