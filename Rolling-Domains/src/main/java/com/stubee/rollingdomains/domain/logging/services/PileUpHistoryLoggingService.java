package com.stubee.rollingdomains.domain.logging.services;

import com.stubee.rollingdomains.domain.logging.model.HistoryLogging;
import com.stubee.rollingdomains.domain.logging.services.commands.PileUpLoggingCommand;

public interface PileUpHistoryLoggingService {

    HistoryLogging pileUp(PileUpLoggingCommand command);

}