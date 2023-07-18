package com.stubee.rollingapplication.domain.company.port.api;

import com.stubee.rollingcore.domain.company.command.DeleteCompanyCommand;

public interface DeleteCompanyUseCase {

    void delete(DeleteCompanyCommand command);

}