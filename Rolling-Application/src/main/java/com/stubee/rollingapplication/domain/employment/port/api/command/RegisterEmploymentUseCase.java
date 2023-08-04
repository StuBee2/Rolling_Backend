package com.stubee.rollingapplication.domain.employment.port.api.command;

import com.stubee.rollingcore.domain.employment.command.RegisterEmploymentCommand;
import com.stubee.rollingcore.domain.employment.model.Employment;

public interface RegisterEmploymentUseCase {

    Employment register(RegisterEmploymentCommand command);

}