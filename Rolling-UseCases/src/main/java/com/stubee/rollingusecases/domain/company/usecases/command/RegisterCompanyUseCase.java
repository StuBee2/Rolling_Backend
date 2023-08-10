package com.stubee.rollingusecases.domain.company.usecases.command;

import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingusecases.domain.company.commands.RegisterCompanyCommand;

public interface RegisterCompanyUseCase {

    Company register(RegisterCompanyCommand command);

}