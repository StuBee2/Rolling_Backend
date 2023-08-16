package com.stubee.rollingdomains.domain.review.model;

import com.stubee.rollingdomains.common.model.Grades;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.member.exception.NotMatchedMemberException;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record Review (
        ReviewId reviewId,
        ReviewDetails reviewDetails,
        Grades reviewGrades,
        MemberId authorId,
        CompanyId companyId) {
    public static Review create(final String content, final String position, final String careerPath,
                                final double salaryAndBenefits, final double workLifeBalance, final double organizationalCulture,
                                final double careerAdvancement, final UUID companyId, MemberId memberId) {
        return Review.builder()
                .reviewDetails(ReviewDetails.create(content, position, careerPath))
                .reviewGrades(Grades.create(salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement))
                .companyId(CompanyId.create(companyId))
                .authorId(memberId)
                .build();
    }

    public static Review createWithId(final ReviewId reviewId, final ReviewDetails reviewDetails, final Grades reviewGrades,
                                      final MemberId memberId, final CompanyId companyId) {
        return Review.builder()
                .reviewId(reviewId)
                .reviewDetails(reviewDetails)
                .reviewGrades(reviewGrades)
                .authorId(memberId)
                .companyId(companyId)
                .build();
    }

    public void isAuthor(final MemberId memberId) {
        if(authorId.equals(memberId)) {
            throw NotMatchedMemberException.EXCEPTION;
        }
    }
}