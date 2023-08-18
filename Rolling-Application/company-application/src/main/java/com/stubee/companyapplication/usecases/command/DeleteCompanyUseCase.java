package com.stubee.companyapplication.usecases.command;

import com.stubee.companyapplication.commands.DeleteCompanyCommand;

public interface DeleteCompanyUseCase {

    void delete(DeleteCompanyCommand command);

}