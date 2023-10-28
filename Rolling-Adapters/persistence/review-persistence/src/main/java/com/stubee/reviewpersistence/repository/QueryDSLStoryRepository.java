package com.stubee.reviewpersistence.repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.persistencecommons.entity.StoryEntity;
import com.stubee.persistencecommons.helper.QueryDSLHelper;
import com.stubee.reviewapplication.usecases.query.response.StoryStatusResponse;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByCompanyResponse;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.stubee.persistencecommons.entity.QCompanyEntity.companyEntity;
import static com.stubee.persistencecommons.entity.QMemberEntity.memberEntity;
import static com.stubee.persistencecommons.entity.QStoryEntity.storyEntity;
import static com.stubee.persistencecommons.helper.ExpressionSupport.Story.*;

@Repository
@RequiredArgsConstructor
public class QueryDSLStoryRepository implements QueryStoryRepository {

    private final QueryDSLHelper<StoryEntity> queryDSLHelper;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public StoryEntity findById(final Long id) {
        return queryDSLHelper.findById(storyEntity, isEqualId(id));
    }

    @Override
    public StoryQueryByCompanyResponse findInfoById(final Long id) {
        return jpaQueryFactory
                .select(queryByCompanyResponse())
                .from(storyEntity)
                .innerJoin(memberEntity)
                .on(storyEntity.memberId.eq(memberEntity.id))
                .where(isEqualId(id))
                .fetchOne();
    }

    @Override
    public StoryStatusResponse findStatusByMemberId(final Long memberId) {
        return jpaQueryFactory
                .select(queryStatusProjection())
                .from(storyEntity)
                .where(isEqualAuthor(memberId))
                .orderBy(storyEntity.modifiedAt.desc())
                .fetchOne();
    }

    @Override
    public List<StoryQueryByMemberResponse> findByMemberId(final Long memberId, final PageRequest pageRequest) {
        return jpaQueryFactory
                .select(queryByMemberResponse())
                .from(storyEntity)
                .innerJoin(companyEntity)
                .on(storyEntity.companyId.eq(companyEntity.id))
                /*.innerJoin(employmentEntity)
                .on(companyEntity.id.eq(employmentEntity.employerId))*/
                .where(isEqualAuthor(memberId)/*.and(isEqualEmployee(memberId))*/)
                .orderBy(storyEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    @Override
    public List<StoryQueryByCompanyResponse> findByCompanyId(final Long companyId, final PageRequest pageRequest) {
        return jpaQueryFactory
                .select(queryByCompanyResponse())
                .from(storyEntity)
                .innerJoin(memberEntity)
                .on(storyEntity.memberId.eq(memberEntity.id))
                .where(isEqualCompany(companyId))
                .orderBy(storyEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    @Override
    public List<StoryEntity> findAll(final PageRequest pageRequest) {
        return queryDSLHelper.findByOptionWithPagination(storyEntity, null, pageRequest, storyEntity.createdAt.desc());
    }

    private ConstructorExpression<StoryStatusResponse> queryStatusProjection() {
        return Projections.constructor(
                StoryStatusResponse.class,
                storyEntity.count(),
                storyEntity.modifiedAt.max());
    }

    private ConstructorExpression<StoryQueryByMemberResponse> queryByMemberResponse() {
        return Projections.constructor(
                StoryQueryByMemberResponse.class,
                storyEntity.id.stringValue(),
                storyEntity.position,
                storyEntity.schoolLife,
                storyEntity.preparationCourse,
                storyEntity.employmentProcess,
                storyEntity.interviewQuestion,
                storyEntity.mostImportantThing,

                storyEntity.welfare,
                storyEntity.commuteTime,
                storyEntity.meal,
                storyEntity.advantages,
                storyEntity.disAdvantages,

                storyEntity.totalGrade,
                storyEntity.salaryAndBenefits,
                storyEntity.workLifeBalance,
                storyEntity.organizationalCulture,
                storyEntity.careerAdvancement,
                storyEntity.createdAt,
                storyEntity.modifiedAt,

                companyEntity.id.stringValue(),
                companyEntity.name,
                companyEntity.logoUrl/*,

                employmentEntity.employmentStatus*/);
    }

    private ConstructorExpression<StoryQueryByCompanyResponse> queryByCompanyResponse() {
        return Projections.constructor(
                StoryQueryByCompanyResponse.class,
                storyEntity.id.stringValue(),
                storyEntity.position,
                storyEntity.schoolLife,
                storyEntity.preparationCourse,
                storyEntity.employmentProcess,
                storyEntity.interviewQuestion,
                storyEntity.mostImportantThing,

                storyEntity.welfare,
                storyEntity.commuteTime,
                storyEntity.meal,
                storyEntity.advantages,
                storyEntity.disAdvantages,

                storyEntity.totalGrade,
                storyEntity.salaryAndBenefits,
                storyEntity.workLifeBalance,
                storyEntity.organizationalCulture,
                storyEntity.careerAdvancement,
                storyEntity.createdAt,
                storyEntity.modifiedAt,

                memberEntity.id.stringValue(),
                memberEntity.nickName,
                memberEntity.socialLoginId,
                memberEntity.imageUrl);
    }

}