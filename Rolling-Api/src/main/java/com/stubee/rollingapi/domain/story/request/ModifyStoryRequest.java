package com.stubee.rollingapi.domain.story.request;

import com.stubee.rollingdomains.domain.story.model.StoryId;
import com.stubee.reviewapplication.usecases.command.ModifyStoryCommand;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModifyStoryRequest(
        @NotBlank
        String position,
        String schoolLife,
        String preparationCourse,
        String employmentProcess,
        String interviewQuestion,
        String mostImportantThing,
        @NotBlank
        String welfare,
        String commuteTime,
        String meal,
        @NotBlank
        String pros,
        @NotBlank
        String cons,
        String etc,
        @NotNull @Min(1) @Max(5) Short salaryAndBenefits,
        @NotNull @Min(1) @Max(5) Short workLifeBalance,
        @NotNull @Min(1) @Max(5) Short organizationalCulture,
        @NotNull @Min(1) @Max(5) Short careerAdvancement
) {
    public ModifyStoryCommand toCommand(Long id) {
        return new ModifyStoryCommand(StoryId.of(id), position, schoolLife, preparationCourse, employmentProcess, interviewQuestion,
                mostImportantThing, welfare, commuteTime, meal, pros, cons, etc,
                salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }
}