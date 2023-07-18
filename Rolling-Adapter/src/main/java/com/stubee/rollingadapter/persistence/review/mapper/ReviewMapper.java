package com.stubee.rollingadapter.persistence.review.mapper;

import com.stubee.rollingadapter.common.annotation.Mapper;
import com.stubee.rollingadapter.common.mapper.GenericMapper;
import com.stubee.rollingadapter.persistence.review.entity.ReviewEntity;
import com.stubee.rollingcore.common.model.Grades;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import com.stubee.rollingcore.domain.member.model.MemberId;
import com.stubee.rollingcore.domain.review.model.Review;
import com.stubee.rollingcore.domain.review.model.ReviewDetails;
import com.stubee.rollingcore.domain.review.model.ReviewId;

@Mapper
public class ReviewMapper implements GenericMapper<ReviewEntity, Review> {

    @Override
    public ReviewEntity toEntity(Review domain) {
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