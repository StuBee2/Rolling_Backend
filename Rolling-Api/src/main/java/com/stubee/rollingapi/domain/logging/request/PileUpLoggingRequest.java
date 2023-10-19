package com.stubee.rollingapi.domain.logging.request;

import com.stubee.rollingdomains.domain.logging.services.commands.PileUpLoggingCommand;
import jakarta.validation.constraints.NotBlank;

public record PileUpLoggingRequest(
        @NotBlank String description,
        @NotBlank String module,
        Long memberId) {
    public PileUpLoggingCommand toCommand() {
        return PileUpLoggingCommand.create(description, module, memberId);
    }
}