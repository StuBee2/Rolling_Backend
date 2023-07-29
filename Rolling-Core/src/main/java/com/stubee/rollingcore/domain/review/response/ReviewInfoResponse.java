package com.stubee.rollingcore.domain.review.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewInfoResponse (
        UUID reviewId,
        String reviewContent,
        String reviewPosition,
        String reviewCareerPath,
        Double totalGrade,
        Double salaryAndBenefits,
        Double workLifeBalance,
        Double organizationalCulture,
        Double careerAdvancement,
        LocalDateTime reviewCreatedAt,
        LocalDateTime reviewModifiedAt,

        UUID writerId,
        String memberNickName,
        String memberSocialId,
        String memberImageUrl) {
}