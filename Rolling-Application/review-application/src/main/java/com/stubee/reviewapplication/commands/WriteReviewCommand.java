package com.stubee.reviewapplication.commands;

import java.util.UUID;

public record WriteReviewCommand(
        UUID companyId,
        String content,
        String position,
        String careerPath,
        Short salaryAndBenefits,
        Short workLifeBalance,
        Short organizationalCulture,
        Short careerAdvancement) {
    public static WriteReviewCommand create(final UUID companyId, final String content, final String position, final String careerPath,
                                            final Short salaryAndBenefits, final Short workLifeBalance,
                                            final Short organizationalCulture, final Short careerAdvancement) {
        return new WriteReviewCommand(companyId, content, position, careerPath,
                salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }
}