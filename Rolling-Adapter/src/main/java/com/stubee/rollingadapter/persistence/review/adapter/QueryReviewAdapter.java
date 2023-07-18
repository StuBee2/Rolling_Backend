package com.stubee.rollingadapter.persistence.review.adapter;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.rollingapplication.common.annotation.Adapter;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.domain.review.response.ReviewInfoResponse;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.domain.review.response.ReviewQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.stubee.rollingadapter.persistence.company.entity.QCompanyEntity.companyEntity;
import static com.stubee.rollingadapter.persistence.member.entity.QMemberEntity.memberEntity;
import static com.stubee.rollingadapter.persistence.review.entity.QReviewEntity.reviewEntity;

@Adapter
@RequiredArgsConstructor
public class QueryReviewAdapter implements QueryReviewPort {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<ReviewInfoResponse> findInfoById(final UUID reviewId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(infoResponseProjection())
                .from(reviewEntity)
                .innerJoin(memberEntity)
                .on(reviewEntity.memberId.eq(memberEntity.id))
                .where(reviewEntity.id.eq(reviewId))
                .fetchOne());
    }

    @Override
    public List<ReviewQueryResponse> findByMemberId(final UUID memberId, PageRequest pageRequest) {
        return jpaQueryFactory
                .select(queryResponseProjection())
                .from(reviewEntity)
                .innerJoin(companyEntity)
                .on(reviewEntity.companyId.eq(companyEntity.id))
                .where(companyEntity.registrantId.eq(memberId))
                .orderBy(reviewEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    @Override
    public List<ReviewInfoResponse> findByCompanyId(final UUID companyId, PageRequest pageRequest) {
        return jpaQueryFactory
                .select(infoResponseProjection())
                .from(reviewEntity)
                .innerJoin(memberEntity)
                .on(reviewEntity.memberId.eq(memberEntity.id))
                .where(reviewEntity.companyId.eq(companyId))
                .orderBy(reviewEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    private ConstructorExpression<ReviewInfoResponse> infoResponseProjection() {
        return Projections.constructor(
                ReviewInfoResponse.class,
                reviewEntity.id,
                reviewEntity.content,
                reviewEntity.position,
                reviewEntity.careerPath,
                reviewEntity.totalGrade,
                reviewEntity.salaryAndBenefits,
                reviewEntity.workLifeBalance,
                reviewEntity.organizationalCulture,
                reviewEntity.careerAdvancement,
                reviewEntity.createdAt,
                reviewEntity.modifiedAt,

                memberEntity.id,
                memberEntity.nickName,
                memberEntity.socialId,
                memberEntity.imageUrl);
    }

    private ConstructorExpression<ReviewQueryResponse> queryResponseProjection() {
        return Projections.constructor(
                ReviewQueryResponse.class,
                reviewEntity.id,
                reviewEntity.content,
                reviewEntity.position,
                reviewEntity.careerPath,
                reviewEntity.totalGrade,
                reviewEntity.salaryAndBenefits,
                reviewEntity.workLifeBalance,
                reviewEntity.organizationalCulture,
                reviewEntity.careerAdvancement,
                reviewEntity.createdAt,
                reviewEntity.modifiedAt,

                companyEntity.id,
                companyEntity.name,
                companyEntity.imgUrl);
    }

}