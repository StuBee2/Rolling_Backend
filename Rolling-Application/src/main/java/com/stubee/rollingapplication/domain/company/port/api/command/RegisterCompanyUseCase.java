package com.stubee.rollingapplication.domain.company.port.api.command;

import com.stubee.rollingcore.domain.company.command.RegisterCompanyCommand;
import com.stubee.rollingcore.domain.company.model.Company;

public interface RegisterCompanyUseCase {

    Company register(RegisterCompanyCommand command);

}