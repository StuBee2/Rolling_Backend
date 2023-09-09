package com.stubee.rollingapi.domain.review.request;

import com.stubee.rollingdomains.domain.review.services.commands.RegisterReviewCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RegisterReviewRequest(
        @NotNull UUID companyId,
        String content,
        @NotBlank String position,
        @NotBlank String careerPath,
        @NotNull Short salaryAndBenefits,
        @NotNull Short workLifeBalance,
        @NotNull Short organizationalCulture,
        @NotNull Short careerAdvancement) {
    public RegisterReviewCommand toCommand() {
        return RegisterReviewCommand.create(
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