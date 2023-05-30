package com.stubee.rollingapplication.domain.logging.port.api;

import com.stubee.rollingcore.domain.logging.dto.command.CreateLoggingCommand;
import com.stubee.rollingcore.domain.logging.model.Logging;

public interface CommandLoggingUseCase {

    Logging create(CreateLoggingCommand request);

}