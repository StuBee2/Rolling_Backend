package com.stubee.employmentpersistence.repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.employmentapplication.usecases.query.response.EmploymentQueryResponse;
import com.stubee.persistencecommons.entity.EmploymentEntity;
import com.stubee.persistencecommons.helper.QueryDSLHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.stubee.persistencecommons.entity.QCompanyEntity.companyEntity;
import static com.stubee.persistencecommons.entity.QEmploymentEntity.employmentEntity;
import static com.stubee.persistencecommons.helper.ExpressionSupport.Employment.*;

@Repository
@RequiredArgsConstructor
public class QueryDSLEmploymentRepository implements QueryEmploymentRepository {

    private final QueryDSLHelper<EmploymentEntity> queryDSLHelper;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<EmploymentQueryResponse> findInfoByEmployeeId(final Long employeeId) {
        return jpaQueryFactory
                .select(queryResponseProjection())
                .from(employmentEntity)
                .innerJoin(companyEntity)
                .on(employmentEntity.employerId.eq(companyEntity.id))
                .where(isEqualEmployee(employeeId))
                .fetch();
    }

    @Override
    public boolean existsByEmployeeIdAndEmployerId(final Long employeeId, final Long employerId) {
        return queryDSLHelper.existsByOption(employmentEntity, isEqualEmployeeAndEmployer(employeeId, employerId)) != null;
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