package com.stubee.loggingapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.loggingapplication.outports.CommandLoggingPort;
import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.rollingdomains.domain.logging.services.PileUpLoggingService;
import com.stubee.rollingdomains.domain.logging.services.commands.PileUpLoggingCommand;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class LoggingService implements PileUpLoggingService {

    private final CommandLoggingPort commandLoggingPort;

    @Override
    public Logging pileUp(final PileUpLoggingCommand command, final MemberId memberId) {
        return commandLoggingPort.save(command.toDomain(memberId));
    }

}