package com.stubee.rollingservices.domain.company.services;

import com.stubee.rollingservices.common.annotations.CommandService;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingports.domain.company.ports.CommandCompanyPort;
import com.stubee.rollingports.domain.company.ports.QueryCompanyPort;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.rollingusecases.domain.company.commands.DeleteCompanyCommand;
import com.stubee.rollingusecases.domain.company.usecases.command.DeleteCompanyUseCase;
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

        Company company = queryCompanyPort.findById(command.companyId().getId())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        company.isRightRegistrant(member.memberId());

        commandCompanyPort.deleteById(company.companyId());
    }

}