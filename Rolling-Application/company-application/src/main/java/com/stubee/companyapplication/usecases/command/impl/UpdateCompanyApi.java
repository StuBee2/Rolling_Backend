package com.stubee.companyapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.companyapplication.usecases.command.UpdateCompanyUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.services.UpdateCompanyListService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@CommandService
@RequiredArgsConstructor
public class UpdateCompanyApi implements UpdateCompanyUseCase {

    private final UpdateCompanyListService updateCompanyListService;

    @Override
    public void updateAll(final List<Company> companyList) {
        updateCompanyListService.updateAll(companyList);
    }

}