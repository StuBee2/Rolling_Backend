package com.stubee.rollingapplication.domain.company.port.api;

import com.stubee.rollingcore.domain.company.dto.command.RegisterCompanyCommand;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.company.model.CompanyId;

public interface CommandCompanyUseCase {

    Company register(RegisterCompanyCommand command);

    void update(Company company);

    void delete(CompanyId companyId);

}