package com.stubee.rollingapi.domain.story.request;

import com.stubee.rollingdomains.domain.story.model.StoryId;
import com.stubee.rollingdomains.domain.story.services.commands.ModifyStoryCommand;

public record ModifyStoryRequest(
        String position,
        String schoolLife,
        String preparationCourse,
        String employmentProcess,
        String interviewQuestion,
        String mostImportantThing,
        String welfare,
        String commuteTime,
        String meal,
        String advantages,
        String disAdvantages
) {
    public ModifyStoryCommand toCommand(Long id) {
        return new ModifyStoryCommand(StoryId.of(id), position, schoolLife, preparationCourse, employmentProcess, interviewQuestion,
                mostImportantThing, welfare, commuteTime, meal, advantages, disAdvantages);
    }
}