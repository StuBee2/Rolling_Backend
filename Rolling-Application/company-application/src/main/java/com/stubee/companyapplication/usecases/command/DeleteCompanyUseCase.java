package com.stubee.companyapplication.usecases.command;

import com.stubee.rollingdomains.domain.company.services.commands.DeleteCompanyCommand;

public interface DeleteCompanyUseCase {

    void delete(DeleteCompanyCommand command);

}