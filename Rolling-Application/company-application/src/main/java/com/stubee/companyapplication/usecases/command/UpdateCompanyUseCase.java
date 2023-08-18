package com.stubee.companyapplication.usecases.command;

import com.stubee.rollingdomains.domain.company.model.Company;

public interface UpdateCompanyUseCase {

    void update(Company company);

}