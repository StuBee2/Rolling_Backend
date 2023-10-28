package com.stubee.companyapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.dtos.response.TSID;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.rollingdomains.domain.company.services.RegisterCompanyService;
import com.stubee.rollingdomains.domain.company.services.commands.RegisterCompanyCommand;
import com.stubee.companyapplication.usecases.command.RegisterCompanyUseCase;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class RegisterCompanyApi implements RegisterCompanyUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;
    private final RegisterCompanyService registerCompanyService;

    @Override
    public TSID register(final RegisterCompanyCommand command) {
        final MemberId memberId = getCurrentMemberPort.getMemberId();

        return TSID.of(registerCompanyService.register(command, memberId).companyId().getId());
    }

}