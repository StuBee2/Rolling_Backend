package com.stubee.rollingapi.domain.story.request;

import com.stubee.rollingdomains.domain.story.model.StoryId;
import com.stubee.rollingdomains.domain.story.services.commands.ModifyStoryCommand;
import jakarta.validation.constraints.NotBlank;

public record ModifyStoryRequest(
        @NotBlank
        String position,
        @NotBlank
        String schoolLife,
        @NotBlank
        String preparationCourse,
        @NotBlank
        String employmentProcess,
        @NotBlank
        String interviewQuestion,
        @NotBlank
        String mostImportantThing,
        @NotBlank
        String welfare,
        String commuteTime,
        @NotBlank
        String meal,
        @NotBlank
        String advantages,
        @NotBlank
        String disAdvantages
) {
    public ModifyStoryCommand toCommand(Long id) {
        return new ModifyStoryCommand(StoryId.of(id), position, schoolLife, preparationCourse, employmentProcess, interviewQuestion,
                mostImportantThing, welfare, commuteTime, meal, advantages, disAdvantages);
    }
}