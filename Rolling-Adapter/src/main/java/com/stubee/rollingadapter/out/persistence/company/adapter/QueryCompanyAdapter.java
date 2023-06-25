package com.stubee.rollingadapter.out.persistence.company.adapter;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.rollingadapter.out.persistence.company.mapper.CompanyMapper;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingcore.common.dto.PageRequest;
import com.stubee.rollingcore.domain.company.dto.response.CompanyQueryResponse;
import com.stubee.rollingcore.domain.company.model.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.stubee.rollingadapter.out.persistence.company.entity.QCompanyEntity.*;
import static com.stubee.rollingadapter.out.persistence.member.entity.QMemberEntity.*;

@Component
@RequiredArgsConstructor
public class QueryCompanyAdapter implements QueryCompanyPort {

    private final JPAQueryFactory jpaQueryFactory;
    private final CompanyMapper companyMapper;

    @Override
    public boolean existsByCompanyId(UUID companyId) {
        return jpaQueryFactory.selectFrom(companyEntity)
                .where(companyEntity.id.eq(companyId))
                .select(companyEntity.id)
                .fetchFirst() == null;
    }

    @Override
    public Optional<CompanyQueryResponse> findById(UUID companyId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(queryResponseProjection())
                .from(companyEntity)
                .innerJoin(memberEntity)
                .on(companyEntity.registrantId.eq(memberEntity.id))
                .where(companyEntity.id.eq(companyId))
                .fetchOne());
    }

    @Override
    public List<Company> findByNameContaining(String companyName, PageRequest pageRequest) {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .where(companyEntity.name.contains(companyName))
                .orderBy(companyEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch()
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findByRegistrantId(UUID registrantId, PageRequest pageRequest) {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .where(companyEntity.registrantId.eq(registrantId))
                .orderBy(companyEntity.createdAt.desc())
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch()
                .stream().map(companyMapper::toDomain).toList();
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
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .orderBy(companyEntity.totalGrade.desc())
                .limit(10)
                .fetch()
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findBySalaryAndBenefits() {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .orderBy(companyEntity.salaryAndBenefits.desc())
                .limit(10)
                .fetch()
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findByWorkLifeBalance() {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .orderBy(companyEntity.workLifeBalance.desc())
                .limit(10)
                .fetch()
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findByOrganizationalCulture() {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .orderBy(companyEntity.organizationalCulture.desc())
                .limit(10)
                .fetch()
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findByCareerAdvancement() {
        return jpaQueryFactory
                .selectFrom(companyEntity)
                .orderBy(companyEntity.careerAdvancement.desc())
                .limit(10)
                .fetch()
                .stream().map(companyMapper::toDomain).toList();
    }

    private ConstructorExpression<CompanyQueryResponse> queryResponseProjection() {
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