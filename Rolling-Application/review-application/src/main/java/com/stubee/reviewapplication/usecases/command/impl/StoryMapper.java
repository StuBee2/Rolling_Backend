package com.stubee.reviewapplication.usecases.command.impl;

import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.model.*;
import com.stubee.reviewapplication.usecases.command.RegisterStoryCommand;
import com.stubee.reviewapplication.usecases.command.ModifyStoryCommand;

public abstract class StoryMapper {

    public static Story toDomain(final RegisterStoryCommand command, final MemberId memberId) {
        return Story.ExceptIdBuilder()
                .storyDetails(StoryDetails.ExceptDateBuilder()
                        .authorId(AuthorId.of(memberId))
                        .companyId(CompanyId.of(command.companyId()))
                        .employmentDetails(EmploymentDetails.builder()
                                .schoolLife(command.schoolLife())
                                .preparationCourse(command.preparationCourse())
                                .employmentProcess(command.employmentProcess())
                                .interviewQuestion(command.interviewQuestion())
                                .mostImportantThing(command.mostImportantThing())
                                .build())
                        .corporationDetails(CorporationDetails.builder()
                                .position(command.position())
                                .welfare(command.welfare())
                                .commuteTime(command.commuteTime())
                                .meal(command.meal())
                                .pros(command.pros())
                                .cons(command.cons())
                                .etc(command.corporationEtc())
                                .build())
                        .build())
                .reviewGrades(ReviewGrades.ExceptTotalBuilder()
                        .salaryAndBenefits(Double.valueOf(command.salaryAndBenefits()))
                        .workLifeBalance(Double.valueOf(command.workLifeBalance()))
                        .organizationalCulture(Double.valueOf(command.organizationalCulture()))
                        .careerAdvancement(Double.valueOf(command.careerAdvancement()))
                        .build())
                .build();
    }

    public static EmploymentDetails toEmploymentDetails(final ModifyStoryCommand command) {
        return EmploymentDetails.builder()
                .schoolLife(command.schoolLife())
                .preparationCourse(command.preparationCourse())
                .employmentProcess(command.employmentProcess())
                .interviewQuestion(command.interviewQuestion())
                .mostImportantThing(command.mostImportantThing())
                .build();
    }

    public static CorporationDetails toCorporationDetails(final ModifyStoryCommand command) {
        return CorporationDetails.builder()
                .position(command.position())
                .welfare(command.welfare())
                .commuteTime(command.commuteTime())
                .meal(command.meal())
                .pros(command.pros())
                .cons(command.cons())
                .etc(command.etc())
                .build();
    }

    public static ReviewGrades toReviewGrades(final ModifyStoryCommand command) {
        return ReviewGrades.ExceptTotalBuilder()
                .salaryAndBenefits(Double.valueOf(command.salaryAndBenefits()))
                .workLifeBalance(Double.valueOf(command.workLifeBalance()))
                .organizationalCulture(Double.valueOf(command.organizationalCulture()))
                .careerAdvancement(Double.valueOf(command.careerAdvancement()))
                .build();
    }

}