package com.stubee.rollingcore.domain.company.dto.response;

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
        String memberSocialId,
        String memberImageUrl) {}