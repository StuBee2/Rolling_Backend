package com.stubee.rollingapi.domain.story.request;

import com.stubee.rollingdomains.domain.story.services.commands.RegisterStoryCommand;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RegisterStoryRequest(
        @NotNull Long companyId,
        @NotNull String position,
        @NotNull String schoolLife,
        @NotNull String preparationCourse,
        @NotNull String employmentProcess,
        @NotNull String interviewQuestion,
        @NotNull String mostImportantThing,

        @NotNull String welfare,
        @NotNull String commuteTime,
        @NotNull String meal,
        @NotNull String advantages,
        @NotNull String disAdvantages,

        @NotNull @Min(1) @Max(5) Short salaryAndBenefits,
        @NotNull @Min(1) @Max(5) Short workLifeBalance,
        @NotNull @Min(1) @Max(5) Short organizationalCulture,
        @NotNull @Min(1) @Max(5) Short careerAdvancement) {
    public RegisterStoryCommand toCommand() {
        return RegisterStoryCommand.of(
                companyId,
                position,
                schoolLife,
                preparationCourse,
                employmentProcess,
                interviewQuestion,
                mostImportantThing,
                welfare,
                commuteTime,
                meal,
                advantages,
                disAdvantages,

                salaryAndBenefits,
                workLifeBalance,
                organizationalCulture,
                careerAdvancement);
    }
}