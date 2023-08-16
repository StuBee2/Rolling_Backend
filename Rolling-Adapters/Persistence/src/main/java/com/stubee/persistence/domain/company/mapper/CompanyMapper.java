package com.stubee.persistence.domain.company.mapper;

import com.stubee.persistence.common.mapper.DomainEntityMapper;
import com.stubee.persistence.common.annotations.Mapper;
import com.stubee.persistence.domain.company.entity.CompanyEntity;
import com.stubee.rollingdomains.common.model.Grades;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.CompanyDetails;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.company.model.RegistrantId;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class CompanyMapper implements DomainEntityMapper<CompanyEntity, Company> {

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
                .registrantId(domain.registrantId().getId())
                .build();
    }

    /** Company Entity With Id */
    public CompanyEntity toEntityWithId(final Company domain) {
        return CompanyEntity.builder()
                .id(domain.companyId().getId())
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
                .registrantId(domain.registrantId().getId())
                .createdAt(domain.companyDetails().createdAt())
                .build();
    }

    @Override
    public Company toDomain(final CompanyEntity entity) {
        if(entity==null) {
            return null;
        }

        return Company.createWithId(CompanyId.create(entity.getId()), companyDetails(entity),
                companyGrades(entity), RegistrantId.create(entity.getRegistrantId()));
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