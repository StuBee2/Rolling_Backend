package com.stubee.rollingadapter.out.persistence.review.mapper;

import com.stubee.rollingadapter.out.common.mapper.GenericMapper;
import com.stubee.rollingadapter.out.persistence.review.entity.ReviewEntity;
import com.stubee.rollingcore.domain.review.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements GenericMapper<ReviewEntity, Review> {

    @Override
    public ReviewEntity toEntity(final Review domain) {
        return ReviewEntity.builder()
                .content(domain.content())
                .position(domain.position())
                .careerPath(domain.careerPath())
                .totalGrade(domain.totalGrade())
                .balanceGrade(domain.balanceGrade())
                .salaryGrade(domain.salaryGrade())
                .welfareGrade(domain.welfareGrade())
                .memberId(domain.memberId())
                .companyId(domain.companyId())
                .build();
    }

    @Override
    public Review toDomain(final ReviewEntity entity) {
        return Review.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .position(entity.getPosition())
                .careerPath(entity.getCareerPath())
                .totalGrade(entity.getTotalGrade())
                .balanceGrade(entity.getBalanceGrade())
                .salaryGrade(entity.getSalaryGrade())
                .welfareGrade(entity.getWelfareGrade())
                .memberId(entity.getMemberId())
                .companyId(entity.getCompanyId())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}