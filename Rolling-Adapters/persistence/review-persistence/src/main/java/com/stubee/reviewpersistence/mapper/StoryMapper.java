package com.stubee.reviewpersistence.mapper;

import com.stubee.persistencecommons.annotations.DomainObjectMapper;
import com.stubee.persistencecommons.entity.StoryEntity;
import com.stubee.rollingdomains.domain.story.model.*;
import com.stubee.rollingdomains.domain.company.model.CompanyId;

@DomainObjectMapper
public class StoryMapper implements com.stubee.persistencecommons.mapper.DomainObjectMapper<StoryEntity, Story> {

    @Override
    public StoryEntity toEntity(final Story domain) {
        return StoryEntity.builder()
                .position(domain.employmentDetails().position())
                .schoolLife(domain.employmentDetails().schoolLife())
                .preparationCourse(domain.employmentDetails().preparationCourse())
                .employmentProcess(domain.employmentDetails().employmentProcess())
                .interviewQuestion(domain.employmentDetails().interviewQuestion())
                .mostImportantThing(domain.employmentDetails().mostImportantThing())

                .welfare(domain.corporationDetails().welfare())
                .commuteTime(domain.corporationDetails().commuteTime())
                .meal(domain.corporationDetails().meal())
                .advantages(domain.corporationDetails().advantages())
                .disAdvantages(domain.corporationDetails().disAdvantages())

                .totalGrade(domain.reviewGrades().getTotal())
                .salaryAndBenefits(domain.reviewGrades().getSalaryAndBenefits())
                .workLifeBalance(domain.reviewGrades().getWorkLifeBalance())
                .organizationalCulture(domain.reviewGrades().getOrganizationalCulture())
                .careerAdvancement(domain.reviewGrades().getCareerAdvancement())

                .memberId(domain.storyDetails().authorId().getId())
                .companyId(domain.storyDetails().companyId().getId())
                .build();
    }

    @Override
    public Story toDomain(final StoryEntity entity) {
        if(entity==null) {
            return null;
        }

        return Story.WithIdBuilder()
                .reviewId(StoryId.of(entity.getId()))
                .employmentDetails(employmentDetails(entity))
                .corporationDetails(corporationDetails(entity))
                .storyDetails(storyDetails(entity))
                .reviewGrades(reviewGrades(entity))
                .build();
    }

    private EmploymentDetails employmentDetails(final StoryEntity entity) {
        return EmploymentDetails.builder()
                .position(entity.getPosition())
                .schoolLife(entity.getSchoolLife())
                .preparationCourse(entity.getPreparationCourse())
                .employmentProcess(entity.getEmploymentProcess())
                .interviewQuestion(entity.getInterviewQuestion())
                .mostImportantThing(entity.getMostImportantThing())
                .build();
    }

    private CorporationDetails corporationDetails(final StoryEntity entity) {
        return CorporationDetails.builder()
                .welfare(entity.getWelfare())
                .commuteTime(entity.getCommuteTime())
                .meal(entity.getMeal())
                .advantages(entity.getAdvantages())
                .disAdvantages(entity.getDisAdvantages())
                .build();
    }

    private StoryDetails storyDetails(final StoryEntity entity) {
        return StoryDetails.WithDateBuilder()
                .authorId(AuthorId.of(entity.getMemberId()))
                .companyId(CompanyId.of(entity.getCompanyId()))
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
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