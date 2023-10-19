package com.stubee.reviewapplication.usecases.query.response;

import java.time.LocalDateTime;

public record StoryQueryByCompanyResponse(
        Long storyId,

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
        String disAdvantages,

        Double total,
        Double salaryAndBenefits,
        Double workLifeBalance,
        Double organizationalCulture,
        Double careerAdvancement,
        LocalDateTime storyCreatedAt,
        LocalDateTime storyModifiedAt,

        Long writerId,
        String memberNickName,
        String memberSocialLoginId,
        String memberImageUrl) {
}