package com.stubee.rollingapplication.domain.company.service;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.api.command.RegisterCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.CommandCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.company.command.RegisterCompanyCommand;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.member.model.MemberId;
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
        return Company.create(
                command.name(), command.address(), command.description(), command.imgUrl(), memberId);
    }

}