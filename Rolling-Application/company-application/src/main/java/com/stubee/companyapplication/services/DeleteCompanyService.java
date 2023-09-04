package com.stubee.companyapplication.services;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.companyapplication.commands.DeleteCompanyCommand;
import com.stubee.companyapplication.outports.command.DeleteCompanyPort;
import com.stubee.companyapplication.outports.query.QueryCompanyPort;
import com.stubee.companyapplication.usecases.command.DeleteCompanyUseCase;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.member.ports.LoadCurrentMemberPort;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class DeleteCompanyService implements DeleteCompanyUseCase {

    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final DeleteCompanyPort deleteCompanyPort;
    private final QueryCompanyPort queryCompanyPort;

    @Override
    public void delete(final DeleteCompanyCommand command) {
        final Company company = queryCompanyPort.findById(command.companyId().getId())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        company.isRegistrant(loadCurrentMemberPort.getMemberId());

        deleteCompanyPort.deleteById(company.companyId());
    }

}