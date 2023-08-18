package com.stubee.companyapplication.services;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.member.MemberSecurityPort;
import com.stubee.companyapplication.commands.RegisterCompanyCommand;
import com.stubee.companyapplication.outports.CommandCompanyPort;
import com.stubee.companyapplication.usecases.command.RegisterCompanyUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.RegistrantId;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class RegisterCompanyService implements RegisterCompanyUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandCompanyPort commandCompanyPort;

    @Override
    public Company register(RegisterCompanyCommand command) {
        return commandCompanyPort.create(createExceptCompanyId(command, RegistrantId.create(memberSecurityPort.getCurrentMemberId().getId())));
    }

    private Company createExceptCompanyId(RegisterCompanyCommand command, RegistrantId registrantId) {
        return Company.create(command.name(), command.address(), command.description(), command.imgUrl(), registrantId);
    }

}