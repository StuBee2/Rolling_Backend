package com.stubee.rollingdomains.domain.review.services.commands;

import com.stubee.rollingdomains.common.model.Grades;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.review.model.AuthorId;
import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingdomains.domain.review.model.ReviewDetails;

import java.util.UUID;

public record RegisterReviewCommand(
        UUID companyId,
        String content,
        String position,
        String careerPath,
        Short salaryAndBenefits,
        Short workLifeBalance,
        Short organizationalCulture,
        Short careerAdvancement) {
    public static RegisterReviewCommand create(final UUID companyId, final String content, final String position, final String careerPath,
                                               final Short salaryAndBenefits, final Short workLifeBalance,
                                               final Short organizationalCulture, final Short careerAdvancement) {
        return new RegisterReviewCommand(companyId, content, position, careerPath,
                salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

    public Review toDomain(final MemberId memberId) {
        return Review.ExceptIdBuilder()
                .reviewDetails(ReviewDetails.ExceptDateBuilder()
                        .content(content)
                        .position(position)
                        .careerPath(careerPath)
                        .build())
                .reviewGrades(Grades.ExceptTotalBuilder()
                        .salaryAndBenefits(salaryAndBenefits)
                        .workLifeBalance(workLifeBalance)
                        .organizationalCulture(organizationalCulture)
                        .careerAdvancement(careerAdvancement)
                        .build())
                .authorId(AuthorId.of(memberId))
                .companyId(CompanyId.of(companyId))
                .build();
    }
}