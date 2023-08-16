package com.stubee.rollingservices.domain.company.services;

import com.stubee.rollingdomains.domain.company.model.RegistrantId;
import com.stubee.rollingservices.common.annotations.CommandService;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingports.domain.company.ports.CommandCompanyPort;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.rollingusecases.domain.company.commands.RegisterCompanyCommand;
import com.stubee.rollingusecases.domain.company.usecases.command.RegisterCompanyUseCase;
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