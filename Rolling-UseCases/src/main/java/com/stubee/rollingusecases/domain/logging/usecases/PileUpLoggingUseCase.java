package com.stubee.rollingusecases.domain.logging.usecases;

import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.rollingusecases.domain.logging.commands.CreateLoggingCommand;

public interface PileUpLoggingUseCase {

    Logging pileUp(CreateLoggingCommand request);

}