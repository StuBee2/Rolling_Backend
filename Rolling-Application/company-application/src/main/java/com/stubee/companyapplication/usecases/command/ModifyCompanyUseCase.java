package com.stubee.companyapplication.usecases.command;

import com.stubee.rollingdomains.domain.company.services.commands.ModifyCompanyDetailsCommand;
import com.stubee.rollingdomains.domain.company.services.commands.ModifyCompanyStatusCommand;

public interface ModifyCompanyUseCase {

    void modify(ModifyCompanyDetailsCommand command);

    void modify(ModifyCompanyStatusCommand command);

}