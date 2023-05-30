package com.stubee.rollingcore.domain.logging.dto.command;

import lombok.Builder;

@Builder
public record CreateLoggingCommand(
        String description,
        String module) {}