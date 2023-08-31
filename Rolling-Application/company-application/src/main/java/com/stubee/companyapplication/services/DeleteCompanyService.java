package com.stubee.companyapplication.services;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.companyapplication.commands.DeleteCompanyCommand;
import com.stubee.companyapplication.outports.CommandCompanyPort;
import com.stubee.companyapplication.outports.QueryCompanyPort;
import com.stubee.companyapplication.usecases.command.DeleteCompanyUseCase;
import com.stubee.memberapplicationshared.ports.LoadCurrentMemberPort;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.member.model.Member;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class DeleteCompanyService implements DeleteCompanyUseCase {

    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final CommandCompanyPort commandCompanyPort;
    private final QueryCompanyPort queryCompanyPort;

    @Override
    public void delete(final DeleteCompanyCommand command) {
        final Member member = loadCurrentMemberPort.getMember();

        final Company company = queryCompanyPort.findById(command.companyId().getId())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        company.isRegistrant(member.memberId());

        commandCompanyPort.deleteById(company.companyId());
    }

}