package com.stubee.rollingdomains.domain.story.services.commands;

import com.stubee.rollingdomains.domain.story.model.StoryId;

public record ModifyStoryCommand(
        StoryId id,
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
) {}