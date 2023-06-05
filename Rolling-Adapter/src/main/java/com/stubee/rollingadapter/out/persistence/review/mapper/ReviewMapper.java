package com.stubee.rollingadapter.out.persistence.review.mapper;

import com.stubee.rollingadapter.out.common.mapper.GenericMapper;
import com.stubee.rollingadapter.out.persistence.review.entity.ReviewEntity;
import com.stubee.rollingcore.common.model.Grades;
import com.stubee.rollingcore.domain.review.model.Review;
import com.stubee.rollingcore.domain.review.model.ReviewDetails;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements GenericMapper<ReviewEntity, Review> {

    @Override
    public ReviewEntity toEntity(final Review domain) {
        return ReviewEntity.builder()
                .content(domain.reviewDetails().content())
                .position(domain.reviewDetails().position())
                .careerPath(domain.reviewDetails().careerPath())
                .totalGrade(domain.reviewGrades().totalGrade())
                .balanceGrade(domain.reviewGrades().balanceGrade())
                .salaryGrade(domain.reviewGrades().salaryGrade())
                .welfareGrade(domain.reviewGrades().welfareGrade())
                .memberId(domain.memberId())
                .companyId(domain.companyId())
                .build();
    }

    @Override
    public Review toDomain(final ReviewEntity entity) {
        return Review.builder()
                .id(entity.getId())
                .reviewDetails(reviewDetails(entity))
                .reviewGrades(reviewGrades(entity))
                .memberId(entity.getMemberId())
                .companyId(entity.getCompanyId())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

    private ReviewDetails reviewDetails(final ReviewEntity entity) {
        return ReviewDetails.builder()
                .content(entity.getContent())
                .position(entity.getPosition())
                .careerPath(entity.getCareerPath())
                .build();
    }

    private Grades reviewGrades(final ReviewEntity entity) {
        return Grades.builder()
                .totalGrade(entity.getTotalGrade())
                .balanceGrade(entity.getBalanceGrade())
                .salaryGrade(entity.getSalaryGrade())
                .welfareGrade(entity.getWelfareGrade())
                .build();
    }

}