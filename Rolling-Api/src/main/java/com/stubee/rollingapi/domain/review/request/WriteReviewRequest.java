package com.stubee.rollingapi.domain.review.request;

import com.stubee.reviewapplication.commands.WriteReviewCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record WriteReviewRequest (
        @NotNull UUID companyId,
        String content,
        @NotBlank String position,
        @NotBlank String careerPath,
        @NotNull Short salaryAndBenefits,
        @NotNull Short workLifeBalance,
        @NotNull Short organizationalCulture,
        @NotNull Short careerAdvancement) {
    public WriteReviewCommand toCommand() {
        return WriteReviewCommand.create(
                companyId,
                content,
                position,
                careerPath,
                salaryAndBenefits,
                workLifeBalance,
                organizationalCulture,
                careerAdvancement);
    }
}