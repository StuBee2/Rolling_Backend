package com.stubee.reviewapplication.usecases.query;

import java.time.LocalDateTime;

public record StoryQueryByMemberResponse(
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

        Double total,
        Double salaryAndBenefits,
        Double workLifeBalance,
        Double organizationalCulture,
        Double careerAdvancement,
        LocalDateTime storyCreatedAt,
        LocalDateTime storyModifiedAt,

        String companyId,
        String companyName,
        String companyImgUrl/*,

        EmploymentStatus employmentStatus*/) {}