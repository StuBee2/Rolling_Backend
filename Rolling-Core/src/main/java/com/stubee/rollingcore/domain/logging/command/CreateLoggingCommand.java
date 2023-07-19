package com.stubee.rollingcore.domain.logging.command;

public record CreateLoggingCommand(
        String description,
        String module) {
    public static CreateLoggingCommand create(final String description, final String module) {
        return new CreateLoggingCommand(description, module);
    }
}