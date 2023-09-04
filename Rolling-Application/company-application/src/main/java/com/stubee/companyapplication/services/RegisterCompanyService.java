package com.stubee.companyapplication.services;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.companyapplication.commands.RegisterCompanyCommand;
import com.stubee.companyapplication.outports.command.RegisterCompanyPort;
import com.stubee.companyapplication.usecases.command.RegisterCompanyUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.services.CheckCompanyNameDuplicationService;
import com.stubee.rollingdomains.domain.member.ports.LoadCurrentMemberPort;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class RegisterCompanyService implements RegisterCompanyUseCase {

    private final CheckCompanyNameDuplicationService checkCompanyNameDuplicationService;
    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final RegisterCompanyPort registerCompanyPort;

    @Override
    public Company register(final RegisterCompanyCommand command) {
        checkCompanyNameDuplicationService.check(command.name());

        return registerCompanyPort.register(command.toDomain(loadCurrentMemberPort.getMemberId()));
    }

}