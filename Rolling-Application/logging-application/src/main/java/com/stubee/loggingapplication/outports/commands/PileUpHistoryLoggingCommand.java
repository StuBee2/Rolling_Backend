package com.stubee.loggingapplication.outports.commands;

import com.stubee.rollingdomains.domain.logging.model.HistoryLogging;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public record PileUpHistoryLoggingCommand(
        String description,
        String module,
        Long memberId) {
    public static PileUpHistoryLoggingCommand create(final String description, final String module, final Long memberId) {
        return new PileUpHistoryLoggingCommand(description, module, memberId);
    }

    public HistoryLogging toDomain() {
        return HistoryLogging.ExceptIdBuilder()
                .description(description)
                .module(module)
                .memberId(MemberId.of(memberId))
                .build();
    }
}