package com.stubee.reviewpersistence.adapters;

import com.stubee.persistencecommons.annotations.DomainObjectMapper;
import com.stubee.persistencecommons.entity.StoryEntity;
import com.stubee.rollingdomains.domain.story.model.*;
import com.stubee.rollingdomains.domain.company.model.CompanyId;

@DomainObjectMapper
class StoryMapper implements com.stubee.persistencecommons.mapper.DomainObjectMapper<StoryEntity, Story> {

    @Override
    public StoryEntity toEntity(final Story domain) {
        return StoryEntity.builder()
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

    public StoryEntity toEntityWithId(final Story domain) {
        return StoryEntity.builder()
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

    @Override
    public Story toDomain(final StoryEntity entity) {
        if(entity==null) {
            return null;
        }

        return Story.WithIdBuilder()
                .storyId(StoryId.of(entity.getId()))
                .storyDetails(storyDetails(entity))
                .reviewGrades(reviewGrades(entity))
                .build();
    }

    private StoryDetails storyDetails(final StoryEntity entity) {
        return StoryDetails.WithDateBuilder()
                .authorId(AuthorId.of(entity.getMemberId()))
                .companyId(CompanyId.of(entity.getCompanyId()))
                .employmentDetails(employmentDetails(entity))
                .corporationDetails(corporationDetails(entity))
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

    private EmploymentDetails employmentDetails(final StoryEntity entity) {
        return EmploymentDetails.builder()
                .schoolLife(entity.getSchoolLife())
                .preparationCourse(entity.getPreparationCourse())
                .employmentProcess(entity.getEmploymentProcess())
                .interviewQuestion(entity.getInterviewQuestion())
                .mostImportantThing(entity.getMostImportantThing())
                .build();
    }

    private CorporationDetails corporationDetails(final StoryEntity entity) {
        return CorporationDetails.builder()
                .position(entity.getPosition())
                .welfare(entity.getWelfare())
                .commuteTime(entity.getCommuteTime())
                .meal(entity.getMeal())
                .pros(entity.getAdvantages())
                .cons(entity.getDisAdvantages())
                .build();
    }

    private ReviewGrades reviewGrades(final StoryEntity entity) {
        return ReviewGrades.WithTotalBuilder()
                .total(entity.getTotalGrade())
                .salaryAndBenefits(entity.getSalaryAndBenefits())
                .workLifeBalance(entity.getWorkLifeBalance())
                .organizationalCulture(entity.getOrganizationalCulture())
                .careerAdvancement(entity.getCareerAdvancement())
                .build();
    }

}