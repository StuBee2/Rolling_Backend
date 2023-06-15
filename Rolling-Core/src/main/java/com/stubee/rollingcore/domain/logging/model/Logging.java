package com.stubee.rollingcore.domain.logging.model;

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
    public static Logging createExceptLoggingId(final String description, final String module, MemberId memberId) {
        return Logging.builder()
                .description(description)
                .module(module)
                .memberId(memberId)
                .build();
    }
}