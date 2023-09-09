package com.stubee.companypersistence.repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.persistencecommons.entity.CompanyEntity;
import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.companyapplication.usecases.query.response.CompanyQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.stubee.persistencecommons.entity.QCompanyEntity.companyEntity;
import static com.stubee.persistencecommons.entity.QMemberEntity.memberEntity;

@Repository
@RequiredArgsConstructor
public class QueryDSLCompanyRepository implements QueryCompanyRepository {

    private static final int RANK_LIMIT = 10;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public boolean existsByCompanyId(UUID companyId) {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .where(companyEntity.id.eq(companyId))
                .select(companyEntity.id)
                .fetchFirst()==null;
    }

    @Override
    public boolean existsByCompanyName(String companyName) {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .where(companyEntity.name.eq(companyName))
                .select(companyEntity.id)
                .fetchFirst()==null;
    }

    @Override
    public CompanyEntity findById(UUID id) {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .where(companyEntity.id.eq(id))
                .fetchOne();
    }

    @Override
    public CompanyQueryResponse findInfoById(UUID companyId) {
        return jpaQueryFactory
                .select(responseProjection())
                .from(companyEntity)
                .innerJoin(memberEntity)
                .on(companyEntity.registrantId.eq(memberEntity.id))
                .where(companyEntity.id.eq(companyId))
                .fetchOne();
    }

    @Override
    public List<CompanyEntity> findByNameContaining(String companyName, PageRequest pageRequest) {
        return findByOptionWithPaging(companyEntity.name.contains(companyName), pageRequest);
    }

    @Override
    public List<CompanyEntity> findByRegistrantId(UUID registrantId, PageRequest pageRequest) {
        return findByOptionWithPaging(companyEntity.registrantId.eq(registrantId), pageRequest);
    }

    @Override
    public List<CompanyEntity> findAll(PageRequest pageRequest) {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    @Override
    public List<CompanyEntity> findByTotalGrade() {
        return queryTop10orderByGrades(companyEntity.totalGrade);
    }

    @Override
    public List<CompanyEntity> findBySalaryAndBenefits() {
        return queryTop10orderByGrades(companyEntity.salaryAndBenefits);
    }

    @Override
    public List<CompanyEntity> findByWorkLifeBalance() {
        return queryTop10orderByGrades(companyEntity.workLifeBalance);
    }

    @Override
    public List<CompanyEntity> findByOrganizationalCulture() {
        return queryTop10orderByGrades(companyEntity.organizationalCulture);
    }

    @Override
    public List<CompanyEntity> findByCareerAdvancement() {
        return queryTop10orderByGrades(companyEntity.careerAdvancement);
    }

    private List<CompanyEntity> findByOptionWithPaging(Predicate option, PageRequest pageRequest) {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .where(option)
                .orderBy(companyEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    private List<CompanyEntity> queryTop10orderByGrades(NumberPath<Double> order) {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .orderBy(order.desc())
                .limit(RANK_LIMIT)
                .fetch();
    }

    private ConstructorExpression<CompanyQueryResponse> responseProjection() {
        return Projections.constructor(CompanyQueryResponse.class,
                companyEntity.id,
                companyEntity.name,
                companyEntity.address,
                companyEntity.description,
                companyEntity.imgUrl,
                companyEntity.totalGrade,
                companyEntity.salaryAndBenefits,
                companyEntity.workLifeBalance,
                companyEntity.organizationalCulture,
                companyEntity.careerAdvancement,
                companyEntity.createdAt,
                companyEntity.modifiedAt,

                memberEntity.id,
                memberEntity.nickName,
                memberEntity.socialLoginId,
                memberEntity.imageUrl);
    }

}