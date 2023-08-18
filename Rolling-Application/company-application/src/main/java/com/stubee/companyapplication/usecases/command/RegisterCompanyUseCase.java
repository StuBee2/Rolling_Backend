package com.stubee.companyapplication.usecases.command;

import com.stubee.companyapplication.commands.RegisterCompanyCommand;
import com.stubee.rollingdomains.domain.company.model.Company;

public interface RegisterCompanyUseCase {

    Company register(RegisterCompanyCommand command);

}