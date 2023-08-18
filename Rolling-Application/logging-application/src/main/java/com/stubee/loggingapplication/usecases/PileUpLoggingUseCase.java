package com.stubee.loggingapplication.usecases;

import com.stubee.loggingapplication.commands.CreateLoggingCommand;
import com.stubee.rollingdomains.domain.logging.model.Logging;

public interface PileUpLoggingUseCase {

    Logging pileUp(CreateLoggingCommand request);

}