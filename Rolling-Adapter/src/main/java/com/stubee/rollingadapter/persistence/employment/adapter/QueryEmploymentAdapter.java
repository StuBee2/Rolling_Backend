package com.stubee.rollingadapter.persistence.employment.adapter;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.rollingapplication.domain.employment.port.spi.QueryEmploymentPort;
import com.stubee.rollingcore.domain.employment.response.EmploymentQueryResponse;
import com.stubee.rollinginfrastructure.global.annotation.Adapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import static com.stubee.rollingadapter.persistence.company.entity.QCompanyEntity.companyEntity;
import static com.stubee.rollingadapter.persistence.employment.entity.QEmploymentEntity.employmentEntity;

@Adapter
@RequiredArgsConstructor
public class QueryEmploymentAdapter implements QueryEmploymentPort {

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