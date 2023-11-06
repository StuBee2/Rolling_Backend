package com.stubee.companyapplication.usecases.command;

import com.stubee.rollingdomains.common.model.dtos.response.TSID;

public interface RegisterCompanyUseCase {

    TSID register(RegisterCompanyCommand command);

}