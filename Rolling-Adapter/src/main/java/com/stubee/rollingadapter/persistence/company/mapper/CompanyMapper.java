package com.stubee.rollingadapter.persistence.company.mapper;

import com.stubee.rollingadapter.common.annotation.Mapper;
import com.stubee.rollingadapter.common.mapper.GenericMapper;
import com.stubee.rollingadapter.persistence.company.entity.CompanyEntity;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.company.model.CompanyDetails;
import com.stubee.rollingcore.common.model.Grades;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import com.stubee.rollingcore.domain.member.model.MemberId;

@Mapper
public class CompanyMapper implements GenericMapper<CompanyEntity, Company> {

    /** Company Entity Except Id */
    @Override
    public CompanyEntity toEntity(final Company domain) {
        return CompanyEntity.builder()
                .name(domain.companyDetails().name())
                .address(domain.companyDetails().companyAddress().address())
                .description(domain.companyDetails().description())
                .imgUrl(domain.companyDetails().imgUrl())
                .companyStatus(domain.companyDetails().companyStatus())
                .totalGrade(domain.companyGrades().totalGrade())
                .salaryAndBenefits(domain.companyGrades().salaryAndBenefits())
                .workLifeBalance(domain.companyGrades().workLifeBalance())
                .organizationalCulture(domain.companyGrades().organizationalCulture())
                .careerAdvancement(domain.companyGrades().careerAdvancement())
                .registrantId(domain.registrantId().id())
                .build();
    }

    /** Company Entity With Id */
    public CompanyEntity toEntityWithId(final Company domain) {
        return CompanyEntity.builder()
                .id(domain.companyId().id())
                .name(domain.companyDetails().name())
                .address(domain.companyDetails().companyAddress().address())
                .description(domain.companyDetails().description())
                .imgUrl(domain.companyDetails().imgUrl())
                .companyStatus(domain.companyDetails().companyStatus())
                .totalGrade(domain.companyGrades().totalGrade())
                .salaryAndBenefits(domain.companyGrades().salaryAndBenefits())
                .workLifeBalance(domain.companyGrades().workLifeBalance())
                .organizationalCulture(domain.companyGrades().organizationalCulture())
                .careerAdvancement(domain.companyGrades().careerAdvancement())
                .registrantId(domain.registrantId().id())
                .createdAt(domain.companyDetails().createdAt())
                .build();
    }

    @Override
    public Company toDomain(final CompanyEntity entity) {
        if(entity==null) {
            return null;
        }

        return Company.createWithId(CompanyId.create(entity.getId()), companyDetails(entity),
                companyGrades(entity), MemberId.create(entity.getRegistrantId()));
    }

    private CompanyDetails companyDetails(final CompanyEntity entity) {
        return CompanyDetails.createWithDate(entity.getName(), entity.getAddress(), entity.getDescription(), entity.getImgUrl(),
                entity.getCompanyStatus(), entity.getCreatedAt(), entity.getModifiedAt());
    }

    private Grades companyGrades(final CompanyEntity entity) {
        return Grades.createWithTotal(entity.getTotalGrade(), entity.getSalaryAndBenefits(), entity.getWorkLifeBalance(),
                entity.getOrganizationalCulture(), entity.getCareerAdvancement());
    }

}