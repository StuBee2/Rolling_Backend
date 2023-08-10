package com.stubee.rollingdomains.domain.logging.model;

import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
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

    public static Logging createWithLoggingId(final Long loggingId, final String description,
                                              final String module, UUID memberId, LocalDateTime createdAt) {
        return Logging.builder()
                .id(loggingId)
                .description(description)
                .module(module)
                .memberId(MemberId.create(memberId))
                .createdAt(createdAt)
                .build();
    }
}