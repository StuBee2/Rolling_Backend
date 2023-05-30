package com.stubee.rollingcore.domain.review.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Review (
        UUID id,
        String content,
        String position,
        String careerPath,
        Double totalGrade,
        Short balanceGrade,
        Short salaryGrade,
        Short welfareGrade,
        UUID memberId,
        UUID companyId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {}