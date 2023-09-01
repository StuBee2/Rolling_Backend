package com.stubee.loggingapplication.services;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.loggingapplication.commands.PileUpLoggingCommand;
import com.stubee.loggingapplication.outports.CommandLoggingPort;
import com.stubee.loggingapplication.usecases.PileUpLoggingUseCase;
import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.rollingdomains.domain.member.ports.LoadCurrentMemberPort;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class PileUpLoggingService implements PileUpLoggingUseCase {

    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final CommandLoggingPort commandLoggingPort;

    @Override
    public Logging pileUp(final PileUpLoggingCommand command) {
        return commandLoggingPort.save(command.toDomain(loadCurrentMemberPort.getMemberId()));
    }

}