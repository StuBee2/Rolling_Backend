package com.stubee.rollingservices.domain.company.services;

import com.stubee.rollingcommons.commons.annotations.CommandService;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.member.model.MemberId;
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
        return commandCompanyPort.create(createExceptCompanyId(command, memberSecurityPort.getCurrentMemberId()));
    }

    private Company createExceptCompanyId(RegisterCompanyCommand command, MemberId memberId) {
        return Company.create(command.name(), command.address(), command.description(), command.imgUrl(), memberId);
    }

}