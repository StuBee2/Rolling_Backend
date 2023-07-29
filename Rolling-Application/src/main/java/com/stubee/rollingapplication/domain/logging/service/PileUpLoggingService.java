package com.stubee.rollingapplication.domain.logging.service;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.logging.port.api.PileUpLoggingUseCase;
import com.stubee.rollingapplication.domain.logging.port.spi.CommandLoggingPort;
import com.stubee.rollingcore.domain.logging.command.CreateLoggingCommand;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.logging.model.Logging;
import com.stubee.rollingcore.domain.member.model.MemberId;
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