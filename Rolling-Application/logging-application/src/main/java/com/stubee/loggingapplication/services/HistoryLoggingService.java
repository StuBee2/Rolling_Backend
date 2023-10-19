package com.stubee.loggingapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.loggingapplication.outports.CommandLoggingPort;
import com.stubee.rollingdomains.domain.logging.model.HistoryLogging;
import com.stubee.rollingdomains.domain.logging.services.PileUpHistoryLoggingService;
import com.stubee.rollingdomains.domain.logging.services.commands.PileUpLoggingCommand;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class HistoryLoggingService implements PileUpHistoryLoggingService {

    private final CommandLoggingPort<HistoryLogging> commandLoggingPort;

    @Override
    public HistoryLogging pileUp(final PileUpLoggingCommand command) {
        return commandLoggingPort.save(command.toDomain());
    }

}