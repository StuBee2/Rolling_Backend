package com.stubee.companyapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.companyapplication.usecases.command.ModifyCompanyUseCase;
import com.stubee.rollingdomains.domain.company.services.ModifyCompanyService;
import com.stubee.rollingdomains.domain.company.services.commands.ModifyCompanyDetailsCommand;
import com.stubee.rollingdomains.domain.company.services.commands.ModifyCompanyStatusCommand;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ModifyCompanyApi implements ModifyCompanyUseCase {

    private final ModifyCompanyService modifyCompanyService;
    private final GetCurrentMemberPort getCurrentMemberPort;

    @Override
    public void modify(final ModifyCompanyDetailsCommand command) {
        modifyCompanyService.modify(command, getCurrentMemberPort.getMemberId());
    }

    @Override
    public void modify(final ModifyCompanyStatusCommand command) {
        modifyCompanyService.modify(command);
    }

}