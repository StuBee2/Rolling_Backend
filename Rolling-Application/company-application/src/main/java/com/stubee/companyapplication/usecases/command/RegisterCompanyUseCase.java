package com.stubee.companyapplication.usecases.command;

import com.stubee.applicationcommons.dtos.response.TSID;

public interface RegisterCompanyUseCase {

    TSID register(RegisterCompanyCommand command);

}