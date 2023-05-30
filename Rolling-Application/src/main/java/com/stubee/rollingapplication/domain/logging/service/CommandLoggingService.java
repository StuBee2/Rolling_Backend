package com.stubee.rollingapplication.domain.logging.service;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.logging.port.api.CommandLoggingUseCase;
import com.stubee.rollingapplication.domain.logging.port.spi.CommandLoggingPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.logging.dto.command.CreateLoggingCommand;
import com.stubee.rollingcore.domain.logging.model.Logging;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@CommandService
@RequiredArgsConstructor
public class CommandLoggingService implements CommandLoggingUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandLoggingPort commandLoggingPort;

    @Override
    public Logging create(final CreateLoggingCommand command) {
        return commandLoggingPort.save(toDomain(command, memberSecurityPort.getCurrentMember().id()));
    }

    private Logging toDomain(final CreateLoggingCommand command, final UUID memberId) {
        return Logging.builder()
                .description(command.description())
                .module(command.module())
                .memberId(memberId)
                .build();
    }

}