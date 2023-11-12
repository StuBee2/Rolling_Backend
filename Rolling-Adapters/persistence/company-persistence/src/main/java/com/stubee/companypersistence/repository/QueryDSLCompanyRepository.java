package com.stubee.companypersistence.repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.persistencecommons.entity.CompanyEntity;
import com.stubee.companyapplication.usecases.query.CompanyQueryResponse;
import com.stubee.persistencecommons.helper.OrderByNull;
import com.stubee.persistencecommons.helper.QueryDSLHelper;
import com.stubee.rollingdomains.common.model.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.stubee.persistencecommons.entity.QCompanyEntity.companyEntity;
import static com.stubee.persistencecommons.entity.QMemberEntity.memberEntity;
import static com.stubee.persistencecommons.helper.ExpressionSupport.Company.*;

@Repository
@RequiredArgsConstructor
public class QueryDSLCompanyRepository implements QueryCompanyRepository {

    private static final int RANK_LIMIT = 10;

    private final QueryDSLHelper<CompanyEntity> queryDSLHelper;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CompanyEntity findById(Long id) {
        return queryDSLHelper.findById(companyEntity, isEqualId(id));
    }

    @Override
    public CompanyQueryResponse findInfoById(Long id) {
        return jpaQueryFactory
                .select(responseProjection())
                .from(companyEntity)
                .innerJoin(memberEntity)
                .on(companyEntity.registrantId.eq(memberEntity.id))
                .where(isEqualId(id))
                .fetchOne();
    }

    @Override
    public boolean existsByCompanyId(Long id) {
        return queryDSLHelper.existsByOption(companyEntity, isEqualId(id).and(isAccepted())) == null;
    }

    @Override
    public boolean existsByCompanyName(String companyName) {
        return queryDSLHelper.existsByOption(companyEntity, isEqualName(companyName)) != null;
    }

    @Override
    public List<CompanyEntity> findByNameContaining(String companyName, PageRequest pageRequest) {
        return findByOptionWithPaging(isContainingName(companyName), pageRequest);
    }

    @Override
    public List<CompanyEntity> findByRegistrantId(Long registrantId, PageRequest pageRequest) {
        return findByOptionWithPaging(isEqualRegistrant(registrantId), pageRequest);
    }

    private List<CompanyEntity> findByOptionWithPaging(Predicate option, PageRequest pageRequest) {
        return queryDSLHelper.findByOptionWithPagination(companyEntity, option, pageRequest, companyEntity.createdAt.desc());
    }

    @Override
    public List<CompanyEntity> findAll(PageRequest pageRequest) {
        return queryDSLHelper.findByOptionWithPagination(companyEntity, isAccepted(), pageRequest, OrderByNull.DEFAULT);
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

    private List<CompanyEntity> queryTop10orderByGrades(NumberPath<Double> order) {
        return queryDSLHelper.findWithOrderAndLimit(companyEntity, order.desc(), RANK_LIMIT);
    }

    private ConstructorExpression<CompanyQueryResponse> responseProjection() {
        return Projections.constructor(CompanyQueryResponse.class,
                companyEntity.id.stringValue(),
                companyEntity.name,
                companyEntity.address,
                companyEntity.addressEtc,
                companyEntity.description,
                companyEntity.logoUrl,
                companyEntity.logoRGB,
                companyEntity.totalGrade,
                companyEntity.salaryAndBenefits,
                companyEntity.workLifeBalance,
                companyEntity.organizationalCulture,
                companyEntity.careerAdvancement,
                companyEntity.createdAt,
                companyEntity.modifiedAt,

                memberEntity.id.stringValue(),
                memberEntity.nickName,
                memberEntity.socialLoginId,
                memberEntity.imageUrl);
    }

}