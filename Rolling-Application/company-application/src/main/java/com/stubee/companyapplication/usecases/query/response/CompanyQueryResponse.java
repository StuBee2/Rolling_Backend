package com.stubee.companyapplication.usecases.query.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompanyQueryResponse(
        UUID companyId,
        String companyName,
        String companyAddress,
        String companyDescription,
        String companyImgUrl,
        Double totalGrade,
        Double salaryAndBenefits,
        Double workLifeBalance,
        Double organizationalCulture,
        Double careerAdvancement,
        LocalDateTime companyCreatedAt,
        LocalDateTime companyModifiedAt,

        UUID registrantId,
        String memberNickName,
        String memberSocialLoginId,
        String memberImageUrl) {}