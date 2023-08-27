package com.stubee.loggingapplication.services;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.member.LoadCurrentMemberPort;
import com.stubee.loggingapplication.commands.CreateLoggingCommand;
import com.stubee.loggingapplication.outports.CommandLoggingPort;
import com.stubee.loggingapplication.usecases.PileUpLoggingUseCase;
import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class PileUpLoggingService implements PileUpLoggingUseCase {

    private final LoadCurrentMemberPort memberSecurityPort;
    private final CommandLoggingPort commandLoggingPort;

    @Override
    public Logging pileUp(CreateLoggingCommand command) {
        return commandLoggingPort.save(createExceptLoggingId(command, memberSecurityPort.getCurrentMember().memberId()));
    }

    private Logging createExceptLoggingId(CreateLoggingCommand command, MemberId memberId) {
        return Logging.createExceptLoggingId(command.description(), command.module(), memberId);
    }

}