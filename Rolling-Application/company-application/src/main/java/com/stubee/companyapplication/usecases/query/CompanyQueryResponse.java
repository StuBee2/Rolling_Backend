package com.stubee.companyapplication.usecases.query;

import java.time.LocalDateTime;

public record CompanyQueryResponse(
        String companyId,
        String companyName,
        String companyAddress,
        String companyAddressEtc,
        String companyDescription,
        String companyLogoUrl,
        Integer companyLogoRGB,
        Double total,
        Double salaryAndBenefits,
        Double workLifeBalance,
        Double organizationalCulture,
        Double careerAdvancement,
        LocalDateTime companyCreatedAt,
        LocalDateTime companyModifiedAt,

        String registrantId,
        String memberNickName,
        String memberSocialLoginId,
        String memberImageUrl) {}