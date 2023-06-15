package com.stubee.rollingapplication.domain.company.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompanyQueryResponse(
        UUID companyId,
        String companyName,
        String companyAddress,
        String companyDescription,
        String companyImgUrl,
        Double totalGrade,
        Double balanceGrade,
        Double salaryGrade,
        Double welfareGrade,
        LocalDateTime companyCreatedAt,
        LocalDateTime companyModifiedAt,

        UUID registrantId,
        String memberNickName,
        String memberSocialId,
        String memberImageUrl) {}