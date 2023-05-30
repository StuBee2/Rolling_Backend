package com.stubee.rollingcore.domain.review.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewInfoResponse (
        UUID reviewId,
        String reviewContent,
        String reviewPosition,
        String reviewCareerPath,
        Double totalGrade,
        Short balanceGrade,
        Short salaryGrade,
        Short welfareGrade,
        LocalDateTime reviewCreatedAt,
        LocalDateTime reviewModifiedAt,

        UUID writerId,
        String memberNickName,
        String memberSocialId,
        String memberImageUrl) {}