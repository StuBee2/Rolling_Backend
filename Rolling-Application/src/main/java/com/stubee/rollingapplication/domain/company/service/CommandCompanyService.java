package com.stubee.rollingapplication.domain.company.service;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.api.CommandCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.CommandCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
 import com.stubee.rollingcore.domain.company.command.RegisterCompanyCommand;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class CommandCompanyService implements CommandCompanyUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandCompanyPort commandCompanyPort;

    @Override
    public Company register(RegisterCompanyCommand command) {
        Member member = memberSecurityPort.getCurrentMember();

        return commandCompanyPort.save(createExceptCompanyId(command, member.memberId()));
    }

    @Override
    public void update(Company company) {
        commandCompanyPort.update(company);
    }

    @Override
    public void delete(CompanyId companyId) {
        commandCompanyPort.deleteById(companyId);
    }

    private Company createExceptCompanyId(RegisterCompanyCommand command, MemberId memberId) {
        return Company.createExceptCompanyId(
                command.name(), command.address(), command.description(), command.imgUrl(), memberId);
    }

}