package com.stubee.companyapplication.usecases.command;

import com.stubee.rollingdomains.domain.company.model.Company;

import java.util.List;

public interface UpdateCompanyUseCase {

    void update(Company company);

    void updateAll(List<Company> companyList);

}