package com.stubee.companyapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.companyapplication.outports.command.UpdateCompanyPort;
import com.stubee.companyapplication.usecases.command.UpdateCompanyUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;

@CommandService
@RequiredArgsConstructor
public class UpdateCompanyService implements UpdateCompanyUseCase {

    private final UpdateCompanyPort updateCompanyPort;

    @Override
    public void updateAll(final List<Company> companyList) {
        updateCompanyPort.updateAll(companyList);
    }

}