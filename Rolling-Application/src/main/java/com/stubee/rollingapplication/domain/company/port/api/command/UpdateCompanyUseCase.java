package com.stubee.rollingapplication.domain.company.port.api.command;

import com.stubee.rollingcore.domain.company.model.Company;

public interface UpdateCompanyUseCase {

    void update(Company company);

}