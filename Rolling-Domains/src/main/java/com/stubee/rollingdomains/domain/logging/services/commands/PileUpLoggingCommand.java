package com.stubee.rollingdomains.domain.logging.services.commands;

import com.stubee.rollingdomains.domain.logging.model.HistoryLogging;
import com.stubee.rollingdomains.domain.member.model.MemberId;

import java.util.UUID;

public record PileUpLoggingCommand(
        String description,
        String module,
        UUID memberId) {
    public static PileUpLoggingCommand create(final String description, final String module, final UUID memberId) {
        return new PileUpLoggingCommand(description, module, memberId);
    }

    public HistoryLogging toDomain() {
        return HistoryLogging.ExceptIdBuilder()
                .description(description)
                .module(module)
                .memberId(MemberId.of(memberId))
                .build();
    }
}