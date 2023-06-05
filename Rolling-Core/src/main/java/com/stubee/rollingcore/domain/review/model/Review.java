package com.stubee.rollingcore.domain.review.model;

import com.stubee.rollingcore.common.model.Grades;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Review (
        UUID id,
        ReviewDetails reviewDetails,
        Grades reviewGrades,
        UUID memberId,
        UUID companyId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {}