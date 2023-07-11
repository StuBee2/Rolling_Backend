package com.stubee.rollingapplication.domain.company.service.command;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.api.CommandCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.CommandCompanyPort;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class CommandCompanyService implements CommandCompanyUseCase {

    private final CommandCompanyPort commandCompanyPort;

    @Override
    public void update(Company company) {
        commandCompanyPort.update(company);
    }

    @Override
    public void delete(CompanyId companyId) {
        commandCompanyPort.deleteById(companyId);
    }

}