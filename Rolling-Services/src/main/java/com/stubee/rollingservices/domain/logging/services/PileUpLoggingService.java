package com.stubee.rollingservices.domain.logging.services;

import com.stubee.rollingcommons.commons.annotations.CommandService;
import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingports.domain.logging.ports.CommandLoggingPort;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.rollingusecases.domain.logging.commands.CreateLoggingCommand;
import com.stubee.rollingusecases.domain.logging.usecases.PileUpLoggingUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class PileUpLoggingService implements PileUpLoggingUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandLoggingPort commandLoggingPort;

    @Override
    public Logging pileUp(CreateLoggingCommand command) {
        return commandLoggingPort.save(createExceptLoggingId(command, memberSecurityPort.getCurrentMember().memberId()));
    }

    private Logging createExceptLoggingId(CreateLoggingCommand command, MemberId memberId) {
        return Logging.createExceptLoggingId(command.description(), command.module(), memberId);
    }

}