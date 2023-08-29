package com.stubee.employmentpersistence.repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.employmentapplication.services.query.response.EmploymentQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.stubee.persistencecommons.entity.QCompanyEntity.companyEntity;
import static com.stubee.persistencecommons.entity.QEmploymentEntity.employmentEntity;

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

    @Override
    public boolean existsByEmployeeIdAndEmployerId(UUID employeeId, UUID employerId) {
        return jpaQueryFactory
                .selectFrom(employmentEntity)
                .where(employmentEntity.employeeId.eq(employeeId)
                        .and(employmentEntity.employerId.eq(employerId)))
                .fetchFirst()==null;
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