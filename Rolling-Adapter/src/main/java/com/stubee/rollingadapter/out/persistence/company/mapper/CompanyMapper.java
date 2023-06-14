package com.stubee.rollingadapter.out.persistence.company.mapper;

import com.stubee.rollingadapter.out.common.mapper.GenericMapper;
import com.stubee.rollingadapter.out.persistence.company.entity.CompanyEntity;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.company.model.CompanyDetails;
import com.stubee.rollingcore.common.model.Grades;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import com.stubee.rollingcore.domain.member.model.MemberId;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements GenericMapper<CompanyEntity, Company> {

    @Override
    public CompanyEntity toEntity(Company domain) {
        return CompanyEntity.builder()
                .name(domain.companyDetails().name())
                .address(domain.companyDetails().companyAddress().address())
                .description(domain.companyDetails().description())
                .imgUrl(domain.companyDetails().imgUrl())
                .totalGrade(domain.companyGrades().totalGrade())
                .balanceGrade(domain.companyGrades().balanceGrade())
                .salaryGrade(domain.companyGrades().salaryGrade())
                .welfareGrade(domain.companyGrades().welfareGrade())
                .registrantId(domain.registrantId().id())
                .build();
    }

    public CompanyEntity toEntityWithId(Company domain) {
        return CompanyEntity.builder()
                .id(domain.companyId().id())
                .name(domain.companyDetails().name())
                .address(domain.companyDetails().companyAddress().address())
                .description(domain.companyDetails().description())
                .imgUrl(domain.companyDetails().imgUrl())
                .totalGrade(domain.companyGrades().totalGrade())
                .balanceGrade(domain.companyGrades().balanceGrade())
                .salaryGrade(domain.companyGrades().salaryGrade())
                .welfareGrade(domain.companyGrades().welfareGrade())
                .registrantId(domain.registrantId().id())
                .createdAt(domain.companyDetails().createdAt())
                .build();
    }

    @Override
    public Company toDomain(final CompanyEntity entity) {
        return Company.create(CompanyId.create(entity.getId()), companyDetails(entity),
                companyGrades(entity), MemberId.create(entity.getRegistrantId()));
    }

    private CompanyDetails companyDetails(final CompanyEntity entity) {
        return CompanyDetails.createWithDate(entity.getName(), entity.getAddress(), entity.getDescription(), entity.getImgUrl(),
                entity.getCreatedAt(), entity.getModifiedAt());
    }

    private Grades companyGrades(final CompanyEntity entity) {
        return Grades.createWithTotal(entity.getTotalGrade(), entity.getBalanceGrade(), entity.getSalaryGrade(), entity.getWelfareGrade());
    }

}