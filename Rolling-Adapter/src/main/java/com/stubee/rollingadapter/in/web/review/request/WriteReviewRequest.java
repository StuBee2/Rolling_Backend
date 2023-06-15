package com.stubee.rollingadapter.in.web.review.request;

import com.stubee.rollingapplication.domain.review.command.WriteReviewCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record WriteReviewRequest (
        @NotNull UUID companyId,
        String content,
        @NotBlank String position,
        @NotBlank String careerPath,
        @NotNull Short balanceGrade,
        @NotNull Short salaryGrade,
        @NotNull Short welfareGrade) {
    public WriteReviewCommand toCommand() {
        return WriteReviewCommand.builder()
                .companyId(companyId)
                .content(content)
                .position(position)
                .careerPath(careerPath)
                .balanceGrade(balanceGrade)
                .salaryGrade(salaryGrade)
                .welfareGrade(welfareGrade)
                .build();
    }
}