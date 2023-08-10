package com.stubee.rollingusecases.domain.company.usecases.command;

import com.stubee.rollingusecases.domain.company.commands.DeleteCompanyCommand;

public interface DeleteCompanyUseCase {

    void delete(DeleteCompanyCommand command);

}