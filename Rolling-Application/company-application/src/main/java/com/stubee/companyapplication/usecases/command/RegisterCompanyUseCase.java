package com.stubee.companyapplication.usecases.command;

import com.stubee.applicationcommons.dtos.response.TSID;
import com.stubee.rollingdomains.domain.company.services.commands.RegisterCompanyCommand;

public interface RegisterCompanyUseCase {

    TSID register(RegisterCompanyCommand command);

}