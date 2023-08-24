package com.stubee.companyapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.companyapplication.outports.CommandCompanyPort;
import com.stubee.companyapplication.usecases.command.UpdateCompanyUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;

@CommandService
@RequiredArgsConstructor
public class UpdateCompanyService implements UpdateCompanyUseCase {

    private final CommandCompanyPort commandCompanyPort;

    @Override
    public void update(Company company) {
        commandCompanyPort.update(company);
    }

    @Override
    public void updateAll(List<Company> companyList) {
        commandCompanyPort.updateAll(companyList);
    }

}