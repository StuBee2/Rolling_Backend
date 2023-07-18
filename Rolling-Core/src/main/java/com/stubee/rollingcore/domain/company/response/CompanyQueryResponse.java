package com.stubee.rollingcore.domain.company.response;

import com.stubee.rollingcore.common.exception.NotMatchedMemberException;
import com.stubee.rollingcore.domain.member.model.MemberId;

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
        String memberImageUrl) {
    public void isRegistrant(MemberId memberId) {
        if(!companyId.equals(memberId.id())) {
            throw NotMatchedMemberException.EXCEPTION;
        }
    }
}