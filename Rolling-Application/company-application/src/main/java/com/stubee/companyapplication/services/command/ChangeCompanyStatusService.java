package com.stubee.companyapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.companyapplication.commands.ChangeCompanyStatusCommand;
import com.stubee.companyapplication.outports.command.UpdateCompanyPort;
import com.stubee.companyapplication.outports.query.QueryCompanyByIdPort;
import com.stubee.companyapplication.usecases.command.ChangeCompanyStatusUseCase;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ChangeCompanyStatusService implements ChangeCompanyStatusUseCase {

    private final UpdateCompanyPort updateCompanyPort;
    private final QueryCompanyByIdPort queryCompanyByIdPort;

    @Override
    public void change(final ChangeCompanyStatusCommand command) {
        final Company company = queryCompanyByIdPort.findById(command.companyId())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        updateCompanyPort.update(company.updateStatus(command.isAccepted()));
    }

}