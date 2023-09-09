package com.stubee.companyapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.rollingdomains.domain.company.services.DeleteCompanyService;
import com.stubee.rollingdomains.domain.company.services.commands.DeleteCompanyCommand;
import com.stubee.companyapplication.usecases.command.DeleteCompanyUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class DeleteCompanyApi implements DeleteCompanyUseCase {

    private final DeleteCompanyService deleteCompanyService;

    @Override
    public void delete(final DeleteCompanyCommand command) {
        deleteCompanyService.delete(command);
    }

}