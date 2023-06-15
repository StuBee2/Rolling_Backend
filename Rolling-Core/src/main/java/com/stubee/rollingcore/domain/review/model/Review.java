package com.stubee.rollingcore.domain.review.model;

import com.stubee.rollingcore.common.model.Grades;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import com.stubee.rollingcore.domain.member.model.MemberId;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record Review (
        ReviewId reviewId,
        ReviewDetails reviewDetails,
        Grades reviewGrades,
        MemberId memberId,
        CompanyId companyId) {
    public static Review create(final String content, final String position, final String careerPath,
                                final double balanceGrade, final double salaryGrade, final double welfareGrade,
                                final UUID companyId, MemberId memberId) {
        return Review.builder()
                .reviewDetails(ReviewDetails.create(content, position, careerPath))
                .reviewGrades(Grades.create(balanceGrade, salaryGrade, welfareGrade))
                .companyId(CompanyId.create(companyId))
                .memberId(memberId)
                .build();
    }

    public static Review createWithId(ReviewId reviewId, ReviewDetails reviewDetails, Grades reviewGrades,
                                      MemberId memberId, CompanyId companyId) {
        return Review.builder()
                .reviewId(reviewId)
                .reviewDetails(reviewDetails)
                .reviewGrades(reviewGrades)
                .memberId(memberId)
                .companyId(companyId)
                .build();
    }
}