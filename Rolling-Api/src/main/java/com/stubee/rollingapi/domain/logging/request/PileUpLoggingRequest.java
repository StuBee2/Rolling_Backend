package com.stubee.rollingapi.domain.logging.request;

import com.stubee.loggingapplication.commands.PileUpLoggingCommand;
import jakarta.validation.constraints.NotBlank;

public record PileUpLoggingRequest(
        @NotBlank String description,
        @NotBlank String module) {
    public PileUpLoggingCommand toCommand() {
        return PileUpLoggingCommand.create(description, module);
    }
}