package com.stubee.rollingcore.domain.logging.model;

import com.stubee.rollingcore.domain.logging.dto.command.CreateLoggingCommand;
import com.stubee.rollingcore.domain.member.model.MemberId;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Logging (
        Long id,
        String description,
        String module,
        MemberId memberId,
        LocalDateTime createdAt) {
    public static Logging createExceptLoggingId(CreateLoggingCommand command, MemberId memberId) {
        return Logging.builder()
                .description(command.description())
                .module(command.module())
                .memberId(memberId)
                .build();
    }
}