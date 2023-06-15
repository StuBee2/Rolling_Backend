package com.stubee.rollingcore.domain.review.command;

import lombok.Builder;

import java.util.UUID;

@Builder
public record WriteReviewCommand(
        UUID companyId,
        String content,
        String position,
        String careerPath,
        Short balanceGrade,
        Short salaryGrade,
        Short welfareGrade) {}