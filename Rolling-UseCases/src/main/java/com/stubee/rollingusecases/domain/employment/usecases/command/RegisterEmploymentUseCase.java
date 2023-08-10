package com.stubee.rollingusecases.domain.employment.usecases.command;

import com.stubee.rollingdomains.domain.employment.model.Employment;
import com.stubee.rollingusecases.domain.employment.commands.RegisterEmploymentCommand;

public interface RegisterEmploymentUseCase {

    Employment register(RegisterEmploymentCommand command);

}