package com.stubee.companyapplication.usecases.query.response;

import java.time.LocalDateTime;

public record CompanyQueryResponse(
        Long companyId,
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

        Long registrantId,
        String memberNickName,
        String memberSocialLoginId,
        String memberImageUrl) {}