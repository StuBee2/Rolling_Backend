package com.stubee.companyapplication.usecases.command;

import com.stubee.rollingdomains.domain.company.services.commands.ChangeCompanyStatusCommand;

public interface ChangeCompanyStatusUseCase {

    void change(ChangeCompanyStatusCommand command);

}