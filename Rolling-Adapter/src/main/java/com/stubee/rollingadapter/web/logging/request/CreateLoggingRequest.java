package com.stubee.rollingadapter.web.logging.request;

import com.stubee.rollingcore.domain.logging.command.CreateLoggingCommand;
import jakarta.validation.constraints.NotBlank;

public record CreateLoggingRequest(
        @NotBlank String description,
        @NotBlank String module) {
    public CreateLoggingCommand toCommand() {
        return CreateLoggingCommand.create(description, module);
    }
}