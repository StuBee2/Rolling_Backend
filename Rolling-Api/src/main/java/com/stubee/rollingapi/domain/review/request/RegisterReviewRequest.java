package com.stubee.rollingapi.domain.review.request;

import com.stubee.rollingdomains.domain.review.services.commands.RegisterReviewCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record RegisterReviewRequest(
        @NotNull UUID companyId,
        String content,
        @NotBlank String position,
        @NotBlank String careerPath,
        @NotNull @Size(min = 1, max = 5) Short salaryAndBenefits,
        @NotNull @Size(min = 1, max = 5) Short workLifeBalance,
        @NotNull @Size(min = 1, max = 5) Short organizationalCulture,
        @NotNull @Size(min = 1, max = 5) Short careerAdvancement) {
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