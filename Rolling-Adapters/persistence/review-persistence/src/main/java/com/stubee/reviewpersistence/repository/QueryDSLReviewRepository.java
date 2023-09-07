package com.stubee.reviewpersistence.repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.persistencecommons.entity.ReviewEntity;
import com.stubee.reviewapplication.services.query.response.ReviewInfoResponse;
import com.stubee.reviewapplication.services.query.response.ReviewQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.stubee.persistencecommons.entity.QCompanyEntity.companyEntity;
import static com.stubee.persistencecommons.entity.QEmploymentEntity.employmentEntity;
import static com.stubee.persistencecommons.entity.QMemberEntity.memberEntity;
import static com.stubee.persistencecommons.entity.QReviewEntity.reviewEntity;

@Repository
@RequiredArgsConstructor
public class QueryDSLReviewRepository implements QueryReviewRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ReviewEntity findById(final UUID reviewId) {
        return jpaQueryFactory
                .selectFrom(reviewEntity)
                .where(reviewEntity.id.eq(reviewId))
                .fetchOne();
    }

    @Override
    public ReviewInfoResponse findInfoById(final UUID reviewId) {
        return jpaQueryFactory
                .select(infoResponseProjection())
                .from(reviewEntity)
                .innerJoin(memberEntity)
                .on(reviewEntity.memberId.eq(memberEntity.id))
                .where(reviewEntity.id.eq(reviewId))
                .fetchOne();
    }

    @Override
    public List<ReviewQueryResponse> findByMemberId(final UUID memberId, final PageRequest pageRequest) {
        return jpaQueryFactory
                .select(queryResponseProjection())
                .from(reviewEntity)
                .innerJoin(companyEntity)
                .on(reviewEntity.companyId.eq(companyEntity.id))
                .innerJoin(employmentEntity)
                .on(companyEntity.id.eq(employmentEntity.employerId))
                .where(reviewEntity.memberId.eq(memberId)
                        .and(employmentEntity.employeeId.eq(memberId)))
                .orderBy(reviewEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    @Override
    public List<ReviewInfoResponse> findByCompanyId(final UUID companyId, final PageRequest pageRequest) {
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
                memberEntity.socialLoginId,
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
                companyEntity.imgUrl,

                employmentEntity.employmentStatus);
    }

}