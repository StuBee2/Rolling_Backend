package com.stubee.loggingapplication.usecases;

import com.stubee.rollingdomains.domain.logging.services.commands.PileUpLoggingCommand;
import com.stubee.rollingdomains.domain.logging.model.HistoryLogging;

public interface PileUpLoggingUseCase {

    HistoryLogging pileUp(PileUpLoggingCommand request);

}