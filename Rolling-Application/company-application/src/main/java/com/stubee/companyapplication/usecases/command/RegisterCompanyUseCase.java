package com.stubee.companyapplication.usecases.command;

import com.stubee.rollingdomains.domain.company.services.commands.RegisterCompanyCommand;
import com.stubee.rollingdomains.domain.company.model.Company;

public interface RegisterCompanyUseCase {

    Company register(RegisterCompanyCommand command);

}