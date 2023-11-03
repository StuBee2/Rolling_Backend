package com.stubee.employmentapplication.usecases.command;

import com.stubee.rollingdomains.domain.employment.model.Employment;

public interface RegisterEmploymentUseCase {

    Employment register(RegisterEmploymentCommand command);

}