package com.stubee.reviewapplication.usecases.query.response;

import java.time.LocalDateTime;

public record StoryQueryByMemberResponse(
        Long reviewId,

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

        Double totalGrade,
        Double salaryAndBenefits,
        Double workLifeBalance,
        Double organizationalCulture,
        Double careerAdvancement,
        LocalDateTime reviewCreatedAt,
        LocalDateTime reviewModifiedAt,

        Long companyId,
        String companyName,
        String companyImgUrl/*,

        EmploymentStatus employmentStatus*/) {}