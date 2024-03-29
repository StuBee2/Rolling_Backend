package rolling.application.story.interactor.query;

import java.time.LocalDateTime;

public record StoryQueryByCompanyResponse(
        String storyId,

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

        Double total,
        Double salaryAndBenefits,
        Double workLifeBalance,
        Double organizationalCulture,
        Double careerAdvancement,
        LocalDateTime storyCreatedAt,
        LocalDateTime storyModifiedAt,

        String writerId,
        String memberNickName,
        String memberSocialLoginId,
        String memberImageUrl) {
}