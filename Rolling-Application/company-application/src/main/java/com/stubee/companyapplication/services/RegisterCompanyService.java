package com.stubee.companyapplication.services;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.companyapplication.commands.RegisterCompanyCommand;
import com.stubee.companyapplication.outports.CommandCompanyPort;
import com.stubee.companyapplication.usecases.command.RegisterCompanyUseCase;
import com.stubee.companyshared.services.CheckCompanyNameDuplicationService;
import com.stubee.membershared.ports.LoadCurrentMemberPort;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class RegisterCompanyService implements RegisterCompanyUseCase {

    private final CheckCompanyNameDuplicationService checkCompanyNameDuplicationService;
    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final CommandCompanyPort commandCompanyPort;

    @Override
    public Company register(final RegisterCompanyCommand command) {
        checkCompanyNameDuplicationService.check(command.name());

        return commandCompanyPort.create(command.toDomain(loadCurrentMemberPort.getMemberId()));
    }

}