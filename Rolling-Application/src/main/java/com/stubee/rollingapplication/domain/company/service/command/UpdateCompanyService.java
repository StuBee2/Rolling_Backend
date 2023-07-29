package com.stubee.rollingapplication.domain.company.service.command;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.api.command.UpdateCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.CommandCompanyPort;
import com.stubee.rollingcore.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class UpdateCompanyService implements UpdateCompanyUseCase {

    private final CommandCompanyPort commandCompanyPort;

    @Override
    public void update(Company company) {
        commandCompanyPort.update(company);
    }

}