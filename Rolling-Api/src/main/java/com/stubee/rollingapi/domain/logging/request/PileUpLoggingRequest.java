package com.stubee.rollingapi.domain.logging.request;

import com.stubee.loggingapplication.outports.commands.PileUpHistoryLoggingCommand;
import jakarta.validation.constraints.NotBlank;

public record PileUpLoggingRequest(
        @NotBlank String description,
        @NotBlank String module,
        Long memberId) {
    public PileUpHistoryLoggingCommand toCommand() {
        return PileUpHistoryLoggingCommand.create(description, module, memberId);
    }
}