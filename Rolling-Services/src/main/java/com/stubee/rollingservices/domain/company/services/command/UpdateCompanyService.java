package com.stubee.rollingservices.domain.company.services.command;

import com.stubee.rollingservices.common.annotations.CommandService;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingports.domain.company.ports.CommandCompanyPort;
import com.stubee.rollingusecases.domain.company.usecases.command.UpdateCompanyUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class UpdateCompanyService implements UpdateCompanyUseCase {

    private final CommandCompanyPort commandCompanyPort;

    @Override
    public void update(Company company) {
        commandCompanyPort.update(company);
    }

}