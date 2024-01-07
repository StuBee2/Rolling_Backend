package rolling.jpamysql.story;

import rolling.domain.company.model.CompanyId;
import rolling.domain.story.model.*;

abstract class StoryMapper {

    static StoryJPAEntity toEntity(final Story domain) {
        return StoryJPAEntity.builder()
                .position(domain.storyDetails().corporationDetails().position())
                .schoolLife(domain.storyDetails().employmentDetails().schoolLife())
                .preparationCourse(domain.storyDetails().employmentDetails().preparationCourse())
                .employmentProcess(domain.storyDetails().employmentDetails().employmentProcess())
                .interviewQuestion(domain.storyDetails().employmentDetails().interviewQuestion())
                .mostImportantThing(domain.storyDetails().employmentDetails().mostImportantThing())

                .welfare(domain.storyDetails().corporationDetails().welfare())
                .commuteTime(domain.storyDetails().corporationDetails().commuteTime())
                .meal(domain.storyDetails().corporationDetails().meal())
                .advantages(domain.storyDetails().corporationDetails().pros())
                .disAdvantages(domain.storyDetails().corporationDetails().cons())
                .etc(domain.storyDetails().corporationDetails().etc())

                .totalGrade(domain.reviewGrades().getTotal())
                .salaryAndBenefits(domain.reviewGrades().getSalaryAndBenefits())
                .workLifeBalance(domain.reviewGrades().getWorkLifeBalance())
                .organizationalCulture(domain.reviewGrades().getOrganizationalCulture())
                .careerAdvancement(domain.reviewGrades().getCareerAdvancement())

                .memberId(domain.storyDetails().authorId().getId())
                .companyId(domain.storyDetails().companyId().getId())
                .build();
    }

    static StoryJPAEntity toEntityWithId(final Story domain) {
        return StoryJPAEntity.builder()
                .id(domain.storyId().getId())
                .position(domain.storyDetails().corporationDetails().position())
                .schoolLife(domain.storyDetails().employmentDetails().schoolLife())
                .preparationCourse(domain.storyDetails().employmentDetails().preparationCourse())
                .employmentProcess(domain.storyDetails().employmentDetails().employmentProcess())
                .interviewQuestion(domain.storyDetails().employmentDetails().interviewQuestion())
                .mostImportantThing(domain.storyDetails().employmentDetails().mostImportantThing())

                .welfare(domain.storyDetails().corporationDetails().welfare())
                .commuteTime(domain.storyDetails().corporationDetails().commuteTime())
                .meal(domain.storyDetails().corporationDetails().meal())
                .advantages(domain.storyDetails().corporationDetails().pros())
                .disAdvantages(domain.storyDetails().corporationDetails().cons())
                .etc(domain.storyDetails().corporationDetails().etc())

                .totalGrade(domain.reviewGrades().getTotal())
                .salaryAndBenefits(domain.reviewGrades().getSalaryAndBenefits())
                .workLifeBalance(domain.reviewGrades().getWorkLifeBalance())
                .organizationalCulture(domain.reviewGrades().getOrganizationalCulture())
                .careerAdvancement(domain.reviewGrades().getCareerAdvancement())

                .memberId(domain.storyDetails().authorId().getId())
                .companyId(domain.storyDetails().companyId().getId())
                .createdAt(domain.storyDetails().createdAt())
                .modifiedAt(domain.storyDetails().modifiedAt())
                .build();
    }

    static Story toDomain(final StoryJPAEntity entity) {
        if(entity==null) {
            return null;
        }

        return Story.WithIdBuilder()
                .storyId(StoryId.of(entity.getId()))
                .storyDetails(storyDetails(entity))
                .reviewGrades(reviewGrades(entity))
                .build();
    }

    private static StoryDetails storyDetails(final StoryJPAEntity entity) {
        return StoryDetails.builder()
                .authorId(AuthorId.of(entity.getMemberId()))
                .companyId(CompanyId.of(entity.getCompanyId()))
                .employmentDetails(employmentDetails(entity))
                .corporationDetails(corporationDetails(entity))
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

    private static EmploymentDetails employmentDetails(final StoryJPAEntity entity) {
        return EmploymentDetails.builder()
                .schoolLife(entity.getSchoolLife())
                .preparationCourse(entity.getPreparationCourse())
                .employmentProcess(entity.getEmploymentProcess())
                .interviewQuestion(entity.getInterviewQuestion())
                .mostImportantThing(entity.getMostImportantThing())
                .build();
    }

    private static CorporationDetails corporationDetails(final StoryJPAEntity entity) {
        return CorporationDetails.builder()
                .position(entity.getPosition())
                .welfare(entity.getWelfare())
                .commuteTime(entity.getCommuteTime())
                .meal(entity.getMeal())
                .pros(entity.getAdvantages())
                .cons(entity.getDisAdvantages())
                .build();
    }

    private static ReviewGrades reviewGrades(final StoryJPAEntity entity) {
        return ReviewGrades.builder()
                .total(entity.getTotalGrade())
                .salaryAndBenefits(entity.getSalaryAndBenefits())
                .workLifeBalance(entity.getWorkLifeBalance())
                .organizationalCulture(entity.getOrganizationalCulture())
                .careerAdvancement(entity.getCareerAdvancement())
                .build();
    }

}