package rolling.jpamysql.story;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import rolling.application.story.interactor.query.StoryQueryByCompanyResponse;
import rolling.application.story.interactor.query.StoryQueryByMemberResponse;
import rolling.application.story.interactor.query.StoryStatusResponse;
import rolling.domain.common.model.PageRequest;

import java.util.List;

import static rolling.jpamysql.company.QCompanyJPAEntity.*;
import static rolling.jpamysql.member.QMemberJPAEntity.*;
import static rolling.jpamysql.story.QStoryJPAEntity.*;

@Repository
@RequiredArgsConstructor
class StoryQueryDSLRepository {

    private final JPAQueryFactory jpaQueryFactory;

    StoryJPAEntity findById(final Long id) {
        return jpaQueryFactory
                .selectFrom(storyJPAEntity)
                .where(storyJPAEntity.id.eq(id))
                .fetchOne();
    }

    StoryQueryByCompanyResponse findInfoById(final Long id) {
        return jpaQueryFactory
                .select(queryByCompanyResponse())
                .from(storyJPAEntity)
                .innerJoin(memberJPAEntity)
                .on(storyJPAEntity.memberId.eq(memberJPAEntity.id))
                .where(storyJPAEntity.id.eq(id))
                .fetchOne();
    }

    StoryStatusResponse findStatusByMemberId(final Long memberId) {
        return jpaQueryFactory
                .select(queryStatusProjection())
                .from(storyJPAEntity)
                .where(storyJPAEntity.memberId.eq(memberId))
                .orderBy(storyJPAEntity.modifiedAt.desc())
                .fetchOne();
    }

    List<StoryQueryByMemberResponse> findByMemberId(final Long memberId, final PageRequest pageRequest) {
        return jpaQueryFactory
                .select(queryByMemberResponse())
                .from(storyJPAEntity)
                .innerJoin(companyJPAEntity)
                .on(storyJPAEntity.companyId.eq(companyJPAEntity.id))
                .where(storyJPAEntity.memberId.eq(memberId))
                .orderBy(storyJPAEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    List<StoryQueryByCompanyResponse> findByCompanyId(final Long companyId, final PageRequest pageRequest) {
        return jpaQueryFactory
                .select(queryByCompanyResponse())
                .from(storyJPAEntity)
                .innerJoin(memberJPAEntity)
                .on(storyJPAEntity.memberId.eq(memberJPAEntity.id))
                .where(storyJPAEntity.companyId.eq(companyId))
                .orderBy(storyJPAEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    List<StoryJPAEntity> findAll(final PageRequest pageRequest) {
        return jpaQueryFactory
                .selectFrom(storyJPAEntity)
                .orderBy(storyJPAEntity.createdAt.desc())
                .offset(pageRequest.page())
                .limit(pageRequest.size())
                .fetch();
    }

    private ConstructorExpression<StoryStatusResponse> queryStatusProjection() {
        return Projections.constructor(
                StoryStatusResponse.class,
                storyJPAEntity.count(),
                storyJPAEntity.modifiedAt.max());
    }

    private ConstructorExpression<StoryQueryByMemberResponse> queryByMemberResponse() {
        return Projections.constructor(
                StoryQueryByMemberResponse.class,
                storyJPAEntity.id.stringValue(),
                storyJPAEntity.position,
                storyJPAEntity.schoolLife,
                storyJPAEntity.preparationCourse,
                storyJPAEntity.employmentProcess,
                storyJPAEntity.interviewQuestion,
                storyJPAEntity.mostImportantThing,

                storyJPAEntity.welfare,
                storyJPAEntity.commuteTime,
                storyJPAEntity.meal,
                storyJPAEntity.advantages,
                storyJPAEntity.disAdvantages,
                storyJPAEntity.etc,

                storyJPAEntity.totalGrade,
                storyJPAEntity.salaryAndBenefits,
                storyJPAEntity.workLifeBalance,
                storyJPAEntity.organizationalCulture,
                storyJPAEntity.careerAdvancement,
                storyJPAEntity.createdAt,
                storyJPAEntity.modifiedAt,

                companyJPAEntity.id.stringValue(),
                companyJPAEntity.name,
                companyJPAEntity.logoUrl);
    }

    private ConstructorExpression<StoryQueryByCompanyResponse> queryByCompanyResponse() {
        return Projections.constructor(
                StoryQueryByCompanyResponse.class,
                storyJPAEntity.id.stringValue(),
                storyJPAEntity.position,
                storyJPAEntity.schoolLife,
                storyJPAEntity.preparationCourse,
                storyJPAEntity.employmentProcess,
                storyJPAEntity.interviewQuestion,
                storyJPAEntity.mostImportantThing,

                storyJPAEntity.welfare,
                storyJPAEntity.commuteTime,
                storyJPAEntity.meal,
                storyJPAEntity.advantages,
                storyJPAEntity.disAdvantages,
                storyJPAEntity.etc,

                storyJPAEntity.totalGrade,
                storyJPAEntity.salaryAndBenefits,
                storyJPAEntity.workLifeBalance,
                storyJPAEntity.organizationalCulture,
                storyJPAEntity.careerAdvancement,
                storyJPAEntity.createdAt,
                storyJPAEntity.modifiedAt,

                memberJPAEntity.id.stringValue(),
                memberJPAEntity.nickName,
                memberJPAEntity.socialLoginId,
                memberJPAEntity.imageUrl);
    }

}