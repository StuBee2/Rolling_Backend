package com.stubee.rollingcore.domain.review.response;

import com.stubee.rollingcore.common.exception.NotMatchedMemberException;
import com.stubee.rollingcore.domain.member.model.MemberId;

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
    public void isAuthor(MemberId writerId) {
        if(!writerId.equals(writerId.id())) {
            throw NotMatchedMemberException.EXCEPTION;
        }
    }
}