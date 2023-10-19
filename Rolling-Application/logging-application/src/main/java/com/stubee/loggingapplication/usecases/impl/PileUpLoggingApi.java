package com.stubee.loggingapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.rollingdomains.domain.logging.services.PileUpHistoryLoggingService;
import com.stubee.rollingdomains.domain.logging.services.commands.PileUpLoggingCommand;
import com.stubee.loggingapplication.usecases.PileUpLoggingUseCase;
import com.stubee.rollingdomains.domain.logging.model.HistoryLogging;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class PileUpLoggingApi implements PileUpLoggingUseCase {

    private final PileUpHistoryLoggingService pileUpLoggingService;

    @Override
    public HistoryLogging pileUp(final PileUpLoggingCommand command) {
        return pileUpLoggingService.pileUp(command);
    }

}