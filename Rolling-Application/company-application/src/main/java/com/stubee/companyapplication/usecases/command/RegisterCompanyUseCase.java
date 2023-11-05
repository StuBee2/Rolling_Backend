package com.stubee.companyapplication.usecases.command;

import com.stubee.applicationcommons.model.response.TSID;

public interface RegisterCompanyUseCase {

    TSID register(RegisterCompanyCommand command);

}