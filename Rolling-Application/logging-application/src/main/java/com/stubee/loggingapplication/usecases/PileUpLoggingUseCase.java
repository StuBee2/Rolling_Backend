package com.stubee.loggingapplication.usecases;

import com.stubee.loggingapplication.outports.commands.PileUpHistoryLoggingCommand;
import com.stubee.rollingdomains.logging.model.HistoryLogging;

public interface PileUpLoggingUseCase {

    HistoryLogging pileUp(PileUpHistoryLoggingCommand request);

}