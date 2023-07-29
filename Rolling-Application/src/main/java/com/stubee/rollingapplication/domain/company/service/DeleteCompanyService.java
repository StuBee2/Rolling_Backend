package com.stubee.rollingapplication.domain.company.service;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.api.command.DeleteCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.CommandCompanyPort;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.company.command.DeleteCompanyCommand;
import com.stubee.rollingcore.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.member.model.Member;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class DeleteCompanyService implements DeleteCompanyUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandCompanyPort commandCompanyPort;
    private final QueryCompanyPort queryCompanyPort;

    @Override
    public void delete(DeleteCompanyCommand command) {
        Member member = memberSecurityPort.getCurrentMember();

        Company company = queryCompanyPort.findById(command.companyId().id())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        company.isRightRegistrant(member.memberId());

        commandCompanyPort.deleteById(company.companyId());
    }

}