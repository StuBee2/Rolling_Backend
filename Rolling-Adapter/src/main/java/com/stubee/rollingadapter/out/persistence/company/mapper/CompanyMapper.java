package com.stubee.rollingadapter.out.persistence.company.mapper;

import com.stubee.rollingadapter.out.common.mapper.GenericMapper;
import com.stubee.rollingadapter.out.persistence.company.entity.CompanyEntity;
import com.stubee.rollingcore.domain.company.model.Address;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.company.model.CompanyDetails;
import com.stubee.rollingcore.common.model.Grades;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements GenericMapper<CompanyEntity, Company> {

    @Override
    public CompanyEntity toEntity(final Company domain) {
        return CompanyEntity.builder()
                .name(domain.companyDetails().name())
                .address(domain.companyDetails().address().address())
                .description(domain.companyDetails().description())
                .imgUrl(domain.companyDetails().imgUrl())
                .totalGrade(domain.companyGrades().totalGrade())
                .balanceGrade(domain.companyGrades().balanceGrade())
                .salaryGrade(domain.companyGrades().salaryGrade())
                .welfareGrade(domain.companyGrades().welfareGrade())
                .registrantId(domain.registrantId())
                .build();
    }

    @Override
    public Company toDomain(final CompanyEntity entity) {
        return Company.builder()
                .id(entity.getId())
                .companyDetails(companyDetails(entity))
                .companyGrades(companyGrade(entity))
                .registrantId(entity.getRegistrantId())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

    private CompanyDetails companyDetails(final CompanyEntity entity) {
        return CompanyDetails.builder()
                .name(entity.getName())
                .address(new Address(entity.getAddress()))
                .description(entity.getDescription())
                .imgUrl(entity.getImgUrl())
                .build();
    }

    private Grades companyGrade(final CompanyEntity entity) {
        return Grades.builder()
                .totalGrade(entity.getTotalGrade())
                .balanceGrade(entity.getBalanceGrade())
                .salaryGrade(entity.getSalaryGrade())
                .welfareGrade(entity.getWelfareGrade())
                .build();
    }

}