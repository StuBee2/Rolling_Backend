package com.stubee.rollingapplication.domain.logging.command;

import lombok.Builder;

@Builder
public record CreateLoggingCommand(
        String description,
        String module) {}