package com.stubee.reviewpersistence.repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.persistencecommons.helper.QueryDSLHelper;
import com.stubee.reviewapplication.usecases.query.response.ReviewStatusResponse;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.persistencecommons.entity.ReviewEntity;
import com.stubee.reviewapplication.usecases.query.response.ReviewInfoResponse;
import com.stubee.reviewapplication.usecases.query.response.ReviewQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.stubee.persistencecommons.entity.QCompanyEntity.companyEntity;
import static com.stubee.persistencecommons.entity.QEmploymentEntity.employmentEntity;
import static com.stubee.persistencecommons.entity.QMemberEntity.memberEntity;
import static com.stubee.persistencecommons.entity.QReviewEntity.reviewEntity;
import static com.stubee.persistencecommons.helper.ExpressionSupport.Employment.isEqualEmployee;
import static com.stubee.persistencecommons.helper.ExpressionSupport.Review.*;

@Repository
@RequiredArgsConstructor
public class QueryDSLReviewRepository implements QueryReviewRepository {

    private final QueryDSLHelper<ReviewEntity> queryDSLHelper;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ReviewEntity findById(final UUID id) {
        return queryDSLHelper.findById(reviewEntity, isEqualId(id));
    }

    @Override
    public ReviewInfoResponse findInfoById(final UUID id) {
        return jpaQueryFactory
                .select(infoResponseProjection())
                .from(reviewEntity)
                .innerJoin(memberEntity)
                .on(reviewEntity.memberId.eq(memberEntity.id))
                .where(isEqualId(id))
                .fetchOne();
    }

    @Override
    public ReviewStatusResponse findByMemberId(final UUID memberId) {
        return jpaQueryFactory
                .select(queryStatusProjection())
                .from(reviewEntity)
                .where(isEqualAuthor(memberId))
                .orderBy(reviewEntity.modifiedAt.desc())
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
                .where(isEqualAuthor(memberId).and(isEqualEmployee(memberId)))
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
                .where(isEqualCompany(companyId))
                .orderBy(reviewEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    @Override
    public List<ReviewEntity> findAll(final PageRequest pageRequest) {
        return queryDSLHelper.findByOptionWithPagination(reviewEntity, null, pageRequest, reviewEntity.createdAt.desc());
    }

    private ConstructorExpression<ReviewStatusResponse> queryStatusProjection() {
        return Projections.constructor(
                ReviewStatusResponse.class,
                reviewEntity.count(),
                reviewEntity.modifiedAt.max());
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

}