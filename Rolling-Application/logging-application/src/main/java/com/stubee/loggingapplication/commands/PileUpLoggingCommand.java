package com.stubee.loggingapplication.commands;

import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public record PileUpLoggingCommand(
        String description,
        String module) {
    public static PileUpLoggingCommand create(final String description, final String module) {
        return new PileUpLoggingCommand(description, module);
    }

    public Logging toDomain(final MemberId memberId) {
        return Logging.createExceptLoggingId(description, module, memberId);
    }
}