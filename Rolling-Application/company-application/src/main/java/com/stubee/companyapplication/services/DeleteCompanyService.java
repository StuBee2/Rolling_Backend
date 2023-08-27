package com.stubee.companyapplication.services;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.member.LoadCurrentMemberPort;
import com.stubee.companyapplication.commands.DeleteCompanyCommand;
import com.stubee.companyapplication.outports.CommandCompanyPort;
import com.stubee.companyapplication.outports.QueryCompanyPort;
import com.stubee.companyapplication.usecases.command.DeleteCompanyUseCase;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.member.model.Member;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class DeleteCompanyService implements DeleteCompanyUseCase {

    private final LoadCurrentMemberPort memberSecurityPort;
    private final CommandCompanyPort commandCompanyPort;
    private final QueryCompanyPort queryCompanyPort;

    @Override
    public void delete(DeleteCompanyCommand command) {
        Member member = memberSecurityPort.getCurrentMember();

        Company company = queryCompanyPort.findById(command.companyId().getId())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        company.isRegistrant(member.memberId());

        commandCompanyPort.deleteById(company.companyId());
    }

}