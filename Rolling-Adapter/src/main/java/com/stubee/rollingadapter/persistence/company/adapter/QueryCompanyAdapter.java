package com.stubee.rollingadapter.persistence.company.adapter;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.rollingapplication.common.annotation.Adapter;
import com.stubee.rollingadapter.persistence.company.mapper.CompanyMapper;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.domain.company.response.CompanyQueryResponse;
import com.stubee.rollingcore.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.stubee.rollingadapter.persistence.company.entity.QCompanyEntity.companyEntity;
import static com.stubee.rollingadapter.persistence.member.entity.QMemberEntity.memberEntity;

@Adapter
@RequiredArgsConstructor
public class QueryCompanyAdapter implements QueryCompanyPort {

    private static final int RANK_LIMIT = 10;

    private final JPAQueryFactory jpaQueryFactory;
    private final CompanyMapper companyMapper;

    @Override
    public boolean existsByCompanyId(UUID companyId) {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .where(companyEntity.id.eq(companyId))
                .select(companyEntity.id)
                .fetchFirst()==null;
    }

    @Override
    public Optional<Company> findById(UUID id) {
        return Optional.ofNullable(companyMapper.toDomain(jpaQueryFactory
                .selectFrom(companyEntity)
                .where(companyEntity.id.eq(id))
                .fetchOne()));
    }

    @Override
    public Optional<CompanyQueryResponse> findInfoById(UUID companyId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(responseProjection())
                .from(companyEntity)
                .innerJoin(memberEntity)
                .on(companyEntity.registrantId.eq(memberEntity.id))
                .where(companyEntity.id.eq(companyId))
                .fetchOne());
    }

    @Override
    public List<Company> findByNameContaining(String companyName, PageRequest pageRequest) {
        return findByOptionWithPaging(companyEntity.name.contains(companyName), pageRequest);
    }

    @Override
    public List<Company> findByRegistrantId(UUID registrantId, PageRequest pageRequest) {
        return findByOptionWithPaging(companyEntity.registrantId.eq(registrantId), pageRequest);
    }

    @Override
    public List<Company> findAll(PageRequest pageRequest) {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch()
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findByTotalGrade() {
        return queryTop10orderByGrades(companyEntity.totalGrade);
    }

    @Override
    public List<Company> findBySalaryAndBenefits() {
        return queryTop10orderByGrades(companyEntity.salaryAndBenefits);
    }

    @Override
    public List<Company> findByWorkLifeBalance() {
        return queryTop10orderByGrades(companyEntity.workLifeBalance);
    }

    @Override
    public List<Company> findByOrganizationalCulture() {
        return queryTop10orderByGrades(companyEntity.organizationalCulture);
    }

    @Override
    public List<Company> findByCareerAdvancement() {
        return queryTop10orderByGrades(companyEntity.careerAdvancement);
    }

    private List<Company> findByOptionWithPaging(Predicate option, PageRequest pageRequest) {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .where(option)
                .orderBy(companyEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch()
                .stream().map(companyMapper::toDomain).toList();
    }

    private List<Company> queryTop10orderByGrades(NumberPath<Double> order) {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .orderBy(order.desc())
                .limit(RANK_LIMIT)
                .fetch()
                .stream().map(companyMapper::toDomain).toList();
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
                memberEntity.socialId,
                memberEntity.imageUrl);
    }

}