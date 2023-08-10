package com.stubee.rollingexternal.persistence.domain.employment.repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.rollingdomains.domain.employment.response.EmploymentQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.stubee.rollingexternal.persistence.domain.company.entity.QCompanyEntity.*;
import static com.stubee.rollingexternal.persistence.domain.employment.entity.QEmploymentEntity.*;

@Repository
@RequiredArgsConstructor
public class QueryDSLEmploymentRepository implements QueryEmploymentRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<EmploymentQueryResponse> findInfoByEmployeeId(final UUID employeeId) {
        return jpaQueryFactory
                .select(queryResponseProjection())
                .from(employmentEntity)
                .innerJoin(companyEntity)
                .on(employmentEntity.employerId.eq(companyEntity.id))
                .where(employmentEntity.employeeId.eq(employeeId))
                .fetch();
    }

    private ConstructorExpression<EmploymentQueryResponse> queryResponseProjection() {
        return Projections.constructor(
                EmploymentQueryResponse.class,
                employmentEntity.employmentStatus,

                employmentEntity.employerId,
                companyEntity.name,
                companyEntity.description,
                companyEntity.address,
                companyEntity.imgUrl);
    }

}