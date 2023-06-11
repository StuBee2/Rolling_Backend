package com.stubee.rollingapplication.domain.logging.service;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.logging.port.api.CommandLoggingUseCase;
import com.stubee.rollingapplication.domain.logging.port.spi.CommandLoggingPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.logging.dto.command.CreateLoggingCommand;
import com.stubee.rollingcore.domain.logging.model.Logging;
import com.stubee.rollingcore.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class CommandLoggingService implements CommandLoggingUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandLoggingPort commandLoggingPort;

    @Override
    public Logging create(CreateLoggingCommand command) {
        return commandLoggingPort.save(createExceptLoggingId(command, memberSecurityPort.getCurrentMember().memberId()));
    }

    private Logging createExceptLoggingId(CreateLoggingCommand command, MemberId memberId) {
        return Logging.createExceptLoggingId(command, memberId);
    }

}