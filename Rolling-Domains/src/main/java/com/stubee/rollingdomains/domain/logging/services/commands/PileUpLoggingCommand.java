package com.stubee.rollingdomains.domain.logging.services.commands;

import com.stubee.rollingdomains.domain.logging.model.HistoryLogging;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public record PileUpLoggingCommand(
        String description,
        String module,
        Long memberId) {
    public static PileUpLoggingCommand create(final String description, final String module, final Long memberId) {
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