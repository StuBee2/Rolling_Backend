package com.stubee.loggingapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.loggingapplication.outports.CommandLoggingPort;
import com.stubee.loggingapplication.outports.commands.PileUpHistoryLoggingCommand;
import com.stubee.loggingapplication.usecases.PileUpLoggingUseCase;
import com.stubee.rollingdomains.logging.model.HistoryLogging;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
class PileUpLoggingApi implements PileUpLoggingUseCase {

    private final CommandLoggingPort<HistoryLogging> commandLoggingPort;

    @Override
    public HistoryLogging pileUp(final PileUpHistoryLoggingCommand command) {
        return commandLoggingPort.save(command.toDomain());
    }

}