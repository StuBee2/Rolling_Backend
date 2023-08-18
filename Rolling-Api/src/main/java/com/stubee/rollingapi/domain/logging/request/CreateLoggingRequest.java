package com.stubee.rollingapi.domain.logging.request;

import com.stubee.loggingapplication.commands.CreateLoggingCommand;
import jakarta.validation.constraints.NotBlank;

public record CreateLoggingRequest(
        @NotBlank String description,
        @NotBlank String module) {
    public CreateLoggingCommand toCommand() {
        return CreateLoggingCommand.create(description, module);
    }
}