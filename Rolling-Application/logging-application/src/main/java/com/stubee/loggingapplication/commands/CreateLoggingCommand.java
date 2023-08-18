package com.stubee.loggingapplication.commands;

public record CreateLoggingCommand(
        String description,
        String module) {
    public static CreateLoggingCommand create(final String description, final String module) {
        return new CreateLoggingCommand(description, module);
    }
}