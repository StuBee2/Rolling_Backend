package rolling.application.story.interactor.command;

import rolling.domain.story.model.StoryId;

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
        String pros,
        String cons,
        String etc,
        Short salaryAndBenefits,
        Short workLifeBalance,
        Short organizationalCulture,
        Short careerAdvancement
) {}