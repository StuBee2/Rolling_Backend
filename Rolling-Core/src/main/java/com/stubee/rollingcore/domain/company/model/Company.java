package com.stubee.rollingcore.domain.company.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Company(
        UUID id,
        String name,
        String address,
        String description,
        String imgUrl,
        Double totalGrade,
        Double balanceGrade,
        Double salaryGrade,
        Double welfareGrade,
        UUID registrantId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {}