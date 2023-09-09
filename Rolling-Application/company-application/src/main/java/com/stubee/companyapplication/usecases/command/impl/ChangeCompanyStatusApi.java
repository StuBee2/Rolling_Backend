package com.stubee.companyapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.rollingdomains.domain.company.services.ChangeCompanyStatusService;
import com.stubee.rollingdomains.domain.company.services.commands.ChangeCompanyStatusCommand;
import com.stubee.companyapplication.usecases.command.ChangeCompanyStatusUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ChangeCompanyStatusApi implements ChangeCompanyStatusUseCase {

    private final ChangeCompanyStatusService changeCompanyStatusService;

    @Override
    public void change(final ChangeCompanyStatusCommand command) {
        changeCompanyStatusService.change(command);
    }

}