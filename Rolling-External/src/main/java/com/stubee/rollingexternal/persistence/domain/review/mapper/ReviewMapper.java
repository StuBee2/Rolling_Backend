package com.stubee.rollingexternal.persistence.domain.review.mapper;

import com.stubee.rollingexternal.persistence.domain.review.entity.ReviewEntity;
import com.stubee.rollingcommons.commons.annotations.Mapper;
import com.stubee.rollingdomains.common.model.Grades;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingdomains.domain.review.model.ReviewDetails;
import com.stubee.rollingdomains.domain.review.model.ReviewId;
import com.stubee.rollingexternal.persistence.common.mapper.DomainEntityMapper;

@Mapper
public class ReviewMapper implements DomainEntityMapper<ReviewEntity, Review> {

    @Override
    public ReviewEntity toEntity(final Review domain) {
        return ReviewEntity.builder()
                .content(domain.reviewDetails().content())
                .position(domain.reviewDetails().position())
                .careerPath(domain.reviewDetails().careerPath())
                .totalGrade(domain.reviewGrades().totalGrade())
                .salaryAndBenefits(domain.reviewGrades().salaryAndBenefits())
                .workLifeBalance(domain.reviewGrades().workLifeBalance())
                .organizationalCulture(domain.reviewGrades().organizationalCulture())
                .careerAdvancement(domain.reviewGrades().careerAdvancement())
                .memberId(domain.authorId().id())
                .companyId(domain.companyId().id())
                .build();
    }

    @Override
    public Review toDomain(final ReviewEntity entity) {
        if(entity==null) {
            return null;
        }

        return Review.createWithId(ReviewId.create(entity.getId()), reviewDetails(entity), reviewGrades(entity),
                MemberId.create(entity.getMemberId()), CompanyId.create(entity.getCompanyId()));
    }

    private ReviewDetails reviewDetails(final ReviewEntity entity) {
        return ReviewDetails.createWithDate(entity.getContent(), entity.getPosition(),
                entity.getCareerPath(), entity.getCreatedAt(), entity.getModifiedAt());
    }

    private Grades reviewGrades(final ReviewEntity entity) {
        return Grades.createWithTotal(entity.getTotalGrade(), entity.getSalaryAndBenefits(), entity.getWorkLifeBalance(),
                entity.getOrganizationalCulture(), entity.getCareerAdvancement());
    }

}