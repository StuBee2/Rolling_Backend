package com.stubee.rollingadapter.out.persistence.review.mapper;

import com.stubee.rollingadapter.out.common.mapper.GenericMapper;
import com.stubee.rollingadapter.out.persistence.review.entity.ReviewEntity;
import com.stubee.rollingcore.common.model.Grades;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import com.stubee.rollingcore.domain.member.model.MemberId;
import com.stubee.rollingcore.domain.review.model.Review;
import com.stubee.rollingcore.domain.review.model.ReviewDetails;
import com.stubee.rollingcore.domain.review.model.ReviewId;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements GenericMapper<ReviewEntity, Review> {

    @Override
    public ReviewEntity toEntity(Review domain) {
        return ReviewEntity.builder()
                .content(domain.reviewDetails().content())
                .position(domain.reviewDetails().position())
                .careerPath(domain.reviewDetails().careerPath())
                .totalGrade(domain.reviewGrades().totalGrade())
                .balanceGrade(domain.reviewGrades().balanceGrade())
                .salaryGrade(domain.reviewGrades().salaryGrade())
                .welfareGrade(domain.reviewGrades().welfareGrade())
                .memberId(domain.memberId().id())
                .companyId(domain.companyId().id())
                .build();
    }

    @Override
    public Review toDomain(final ReviewEntity entity) {
        return Review.create(ReviewId.create(entity.getId()), reviewDetails(entity), reviewGrades(entity),
                MemberId.create(entity.getMemberId()), CompanyId.create(entity.getCompanyId()));
    }

    private ReviewDetails reviewDetails(final ReviewEntity entity) {
        return ReviewDetails.createWithDate(entity.getContent(), entity.getPosition(), entity.getCareerPath(), entity.getCreatedAt(), entity.getModifiedAt());
    }

    private Grades reviewGrades(final ReviewEntity entity) {
        return Grades.createWithTotal(entity.getTotalGrade(), entity.getBalanceGrade(), entity.getSalaryGrade(), entity.getWelfareGrade());
    }

}