package com.stubee.rollingapplication.domain.review.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewQueryResponse (
        UUID reviewId,
        String reviewContent,
        String reviewPosition,
        String reviewCareerPath,
        Double totalGrade,
        Double balanceGrade,
        Double salaryGrade,
        Double welfareGrade,
        LocalDateTime reviewCreatedAt,
        LocalDateTime reviewModifiedAt,

        UUID companyId,
        String companyName,
        String companyImgUrl) {}