package com.stubee.companyapplication.usecases.command;

import com.stubee.rollingdomains.common.model.TSID;

public interface RegisterCompanyUseCase {

    TSID register(RegisterCompanyCommand command);

}