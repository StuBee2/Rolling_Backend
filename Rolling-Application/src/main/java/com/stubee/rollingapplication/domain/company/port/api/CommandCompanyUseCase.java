package com.stubee.rollingapplication.domain.company.port.api;

import com.stubee.rollingcore.domain.company.model.Company;

public interface CommandCompanyUseCase {

    void update(Company company);

}