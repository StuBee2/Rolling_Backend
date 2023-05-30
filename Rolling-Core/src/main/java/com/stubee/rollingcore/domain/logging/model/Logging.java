package com.stubee.rollingcore.domain.logging.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Logging (
        Long id,
        String description,
        String module,
        UUID memberId,
        LocalDateTime createdAt) {}