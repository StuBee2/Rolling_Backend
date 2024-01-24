package rolling.jpamysql.story;

import rolling.domain.company.model.CompanyId;
import rolling.domain.story.model.*;

abstract class StoryMapper {

    static StoryJPAEntity toEntity(final Story domain) {
        return StoryJPAEntity.builder()
                .memberId(domain.authorId().getId())
                .companyId(domain.companyId().getId())

                .position(domain.corporationDetails().position())
                .schoolLife(domain.employmentDetails().schoolLife())
                .preparationCourse(domain.employmentDetails().preparationCourse())
                .employmentProcess(domain.employmentDetails().employmentProcess())
                .interviewQuestion(domain.employmentDetails().interviewQuestion())
                .mostImportantThing(domain.employmentDetails().mostImportantThing())

                .welfare(domain.corporationDetails().welfare())
                .commuteTime(domain.corporationDetails().commuteTime())
                .meal(domain.corporationDetails().meal())
                .advantages(domain.corporationDetails().pros())
                .disAdvantages(domain.corporationDetails().cons())
                .etc(domain.corporationDetails().etc())

                .totalGrade(domain.grades().getTotal())
                .salaryAndBenefits(domain.grades().getSalaryAndBenefits())
                .workLifeBalance(domain.grades().getWorkLifeBalance())
                .organizationalCulture(domain.grades().getOrganizationalCulture())
                .careerAdvancement(domain.grades().getCareerAdvancement())
                .build();
    }

    static StoryJPAEntity toEntityWithId(final Story domain) {
        return StoryJPAEntity.builder()
                .id(domain.id().getId())
                .memberId(domain.authorId().getId())
                .companyId(domain.companyId().getId())

                .position(domain.corporationDetails().position())
                .schoolLife(domain.employmentDetails().schoolLife())
                .preparationCourse(domain.employmentDetails().preparationCourse())
                .employmentProcess(domain.employmentDetails().employmentProcess())
                .interviewQuestion(domain.employmentDetails().interviewQuestion())
                .mostImportantThing(domain.employmentDetails().mostImportantThing())

                .welfare(domain.corporationDetails().welfare())
                .commuteTime(domain.corporationDetails().commuteTime())
                .meal(domain.corporationDetails().meal())
                .advantages(domain.corporationDetails().pros())
                .disAdvantages(domain.corporationDetails().cons())
                .etc(domain.corporationDetails().etc())

                .totalGrade(domain.grades().getTotal())
                .salaryAndBenefits(domain.grades().getSalaryAndBenefits())
                .workLifeBalance(domain.grades().getWorkLifeBalance())
                .organizationalCulture(domain.grades().getOrganizationalCulture())
                .careerAdvancement(domain.grades().getCareerAdvancement())

                .createdAt(domain.createdAt())
                .modifiedAt(domain.modifiedAt())
                .build();
    }

    static Story toDomain(final StoryJPAEntity entity) {
        if(entity==null) {
            return null;
        }

        return Story.WithIdBuilder()
                .id(StoryId.of(entity.getId()))
                .authorId(AuthorId.of(entity.getMemberId()))
                .companyId(CompanyId.of(entity.getCompanyId()))

                .corporationDetails(CorporationDetails.builder()
                        .position(entity.getPosition())
                        .welfare(entity.getWelfare())
                        .commuteTime(entity.getCommuteTime())
                        .meal(entity.getMeal())
                        .pros(entity.getAdvantages())
                        .cons(entity.getDisAdvantages())
                        .build())
                .employmentDetails(EmploymentDetails.builder()
                        .schoolLife(entity.getSchoolLife())
                        .preparationCourse(entity.getPreparationCourse())
                        .employmentProcess(entity.getEmploymentProcess())
                        .interviewQuestion(entity.getInterviewQuestion())
                        .mostImportantThing(entity.getMostImportantThing())
                        .build())
                .grades(ReviewGrades.builder()
                        .total(entity.getTotalGrade())
                        .salaryAndBenefits(entity.getSalaryAndBenefits())
                        .workLifeBalance(entity.getWorkLifeBalance())
                        .organizationalCulture(entity.getOrganizationalCulture())
                        .careerAdvancement(entity.getCareerAdvancement())
                        .build())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}