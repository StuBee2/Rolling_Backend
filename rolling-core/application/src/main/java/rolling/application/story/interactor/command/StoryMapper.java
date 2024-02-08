package rolling.application.story.interactor.command;

import rolling.domain.company.model.CompanyId;
import rolling.domain.member.model.MemberId;
import rolling.domain.story.model.*;

abstract class StoryMapper {

    static Story toDomain(final RegisterStoryCommand command, final MemberId memberId) {
        return Story.ExceptIdBuilder()
                .authorId(AuthorId.of(memberId))
                .companyId(CompanyId.of(command.companyId()))
                .corporationDetails(CorporationDetails.builder()
                        .position(command.position())
                        .welfare(command.welfare())
                        .commuteTime(command.commuteTime())
                        .meal(command.meal())
                        .pros(command.pros())
                        .cons(command.cons())
                        .etc(command.corporationEtc())
                        .build())
                .employmentDetails(EmploymentDetails.builder()
                        .schoolLife(command.schoolLife())
                        .preparationCourse(command.preparationCourse())
                        .employmentProcess(command.employmentProcess())
                        .interviewQuestion(command.interviewQuestion())
                        .mostImportantThing(command.mostImportantThing())
                        .build())
                .reviewGrades(ReviewGrades.ExceptTotalBuilder()
                        .salaryAndBenefits(Double.valueOf(command.salaryAndBenefits()))
                        .workLifeBalance(Double.valueOf(command.workLifeBalance()))
                        .organizationalCulture(Double.valueOf(command.organizationalCulture()))
                        .careerAdvancement(Double.valueOf(command.careerAdvancement()))
                        .build())
                .build();
    }

    static EmploymentDetails toEmploymentDetails(final ModifyStoryCommand command) {
        return EmploymentDetails.builder()
                .schoolLife(command.schoolLife())
                .preparationCourse(command.preparationCourse())
                .employmentProcess(command.employmentProcess())
                .interviewQuestion(command.interviewQuestion())
                .mostImportantThing(command.mostImportantThing())
                .build();
    }

    static CorporationDetails toCorporationDetails(final ModifyStoryCommand command) {
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

    static ReviewGrades toReviewGrades(final ModifyStoryCommand command) {
        return ReviewGrades.ExceptTotalBuilder()
                .salaryAndBenefits(Double.valueOf(command.salaryAndBenefits()))
                .workLifeBalance(Double.valueOf(command.workLifeBalance()))
                .organizationalCulture(Double.valueOf(command.organizationalCulture()))
                .careerAdvancement(Double.valueOf(command.careerAdvancement()))
                .build();
    }

}