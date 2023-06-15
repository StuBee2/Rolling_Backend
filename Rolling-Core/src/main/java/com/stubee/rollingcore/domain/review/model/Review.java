package com.stubee.rollingcore.domain.review.model;

import com.stubee.rollingcore.common.model.Grades;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import com.stubee.rollingcore.domain.member.model.MemberId;
import com.stubee.rollingcore.domain.review.dto.command.WriteReviewCommand;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record Review (
        ReviewId reviewId,
        ReviewDetails reviewDetails,
        Grades reviewGrades,
        MemberId memberId,
        CompanyId companyId) {
    public static Review create(WriteReviewCommand command, MemberId memberId) {
        return Review.builder()
                .reviewDetails(ReviewDetails.create(command.content(), command.position(), command.careerPath()))
                .reviewGrades(Grades.create(command.balanceGrade(), command.salaryGrade(), command.welfareGrade()))
                .companyId(new CompanyId(command.companyId()))
                .memberId(memberId)
                .build();
    }

    public static Review createWithId(ReviewId reviewId, ReviewDetails reviewDetails, Grades reviewGrades, MemberId memberId, CompanyId companyId) {
        return Review.builder()
                .reviewId(reviewId)
                .reviewDetails(reviewDetails)
                .reviewGrades(reviewGrades)
                .memberId(memberId)
                .companyId(companyId)
                .build();
    }
}