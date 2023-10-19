package com.stubee.rollingdomains.domain.story.services.commands;

import com.stubee.rollingdomains.domain.story.model.*;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public record RegisterStoryCommand(
        Long companyId,
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

        Short salaryAndBenefits,
        Short workLifeBalance,
        Short organizationalCulture,
        Short careerAdvancement) {
    public static RegisterStoryCommand of(final Long companyId,
                                          final String position,
                                          final String schoolLife,
                                          final String preparationCourse,
                                          final String employmentProcess,
                                          final String interviewQuestion,
                                          final String mostImportantThing,

                                          final String welfare,
                                          final String commuteTime,
                                          final String meal,
                                          final String advantages,
                                          final String disAdvantages,

                                          final Short salaryAndBenefits, final Short workLifeBalance,
                                          final Short organizationalCulture, final Short careerAdvancement) {
        return new RegisterStoryCommand(companyId,
                position, schoolLife, preparationCourse, employmentProcess, interviewQuestion, mostImportantThing,
                welfare, commuteTime, meal, advantages, disAdvantages,
                salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
    }

    public Story toDomain(final MemberId memberId) {
        return Story.ExceptIdBuilder()
                .employmentDetails(EmploymentDetails.builder()
                        .position(position)
                        .schoolLife(schoolLife)
                        .preparationCourse(preparationCourse)
                        .employmentProcess(employmentProcess)
                        .interviewQuestion(interviewQuestion)
                        .mostImportantThing(mostImportantThing)
                        .build())
                .corporationDetails(CorporationDetails.builder()
                        .welfare(welfare)
                        .commuteTime(commuteTime)
                        .meal(meal)
                        .advantages(advantages)
                        .disAdvantages(disAdvantages)
                        .build())
                .storyDetails(StoryDetails.ExceptDateBuilder()
                        .authorId(AuthorId.of(memberId))
                        .companyId(CompanyId.of(companyId))
                        .build())
                .reviewGrades(ReviewGrades.ExceptTotalBuilder()
                        .salaryAndBenefits(Double.valueOf(salaryAndBenefits))
                        .workLifeBalance(Double.valueOf(workLifeBalance))
                        .organizationalCulture(Double.valueOf(organizationalCulture))
                        .careerAdvancement(Double.valueOf(careerAdvancement))
                        .build())
                .build();
    }
}