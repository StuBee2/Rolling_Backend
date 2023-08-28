package com.stubee.loggingapplication.usecases;

import com.stubee.loggingapplication.commands.PileUpLoggingCommand;
import com.stubee.rollingdomains.domain.logging.model.Logging;

public interface PileUpLoggingUseCase {

    Logging pileUp(PileUpLoggingCommand request);

}