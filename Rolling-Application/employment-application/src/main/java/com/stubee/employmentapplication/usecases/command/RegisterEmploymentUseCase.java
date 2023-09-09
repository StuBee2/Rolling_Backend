package com.stubee.employmentapplication.usecases.command;

import com.stubee.rollingdomains.domain.employment.services.commands.RegisterEmploymentCommand;
import com.stubee.rollingdomains.domain.employment.model.Employment;

public interface RegisterEmploymentUseCase {

    Employment register(RegisterEmploymentCommand command);

}