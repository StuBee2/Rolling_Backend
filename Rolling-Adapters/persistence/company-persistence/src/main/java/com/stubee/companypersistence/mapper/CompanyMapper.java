package com.stubee.companypersistence.mapper;

import com.stubee.persistencecommons.annotations.DomainObjectMapper;
import com.stubee.persistencecommons.entity.CompanyEntity;
import com.stubee.rollingdomains.domain.company.model.CompanyGrades;
import com.stubee.rollingdomains.domain.company.model.*;

import java.util.List;

@DomainObjectMapper
public class CompanyMapper implements com.stubee.persistencecommons.mapper.DomainObjectMapper<CompanyEntity, Company> {

    @Override
    public CompanyEntity toEntity(final Company domain) {

        return CompanyEntity.builder()
                .name(domain.companyDetails().name())
                .address(domain.companyDetails().companyAddress().address())
                .description(domain.companyDetails().description())
                .imgUrl(domain.companyDetails().imgUrl())
                .companyStatus(domain.companyDetails().companyStatus())
                .totalGrade(domain.companyGrades().getTotal())
                .salaryAndBenefits(domain.companyGrades().getSalaryAndBenefits())
                .workLifeBalance(domain.companyGrades().getWorkLifeBalance())
                .organizationalCulture(domain.companyGrades().getOrganizationalCulture())
                .careerAdvancement(domain.companyGrades().getCareerAdvancement())
                .registrantId(domain.registrantId().getId())
                .build();
    }

    public CompanyEntity toEntityWithId(final Company domain) {
        return CompanyEntity.builder()
                .id(domain.companyId().getId())
                .name(domain.companyDetails().name())
                .address(domain.companyDetails().companyAddress().address())
                .description(domain.companyDetails().description())
                .imgUrl(domain.companyDetails().imgUrl())
                .companyStatus(domain.companyDetails().companyStatus())
                .totalGrade(domain.companyGrades().getTotal())
                .salaryAndBenefits(domain.companyGrades().getSalaryAndBenefits())
                .workLifeBalance(domain.companyGrades().getWorkLifeBalance())
                .organizationalCulture(domain.companyGrades().getOrganizationalCulture())
                .careerAdvancement(domain.companyGrades().getCareerAdvancement())
                .registrantId(domain.registrantId().getId())
                .createdAt(domain.companyDetails().createdAt())
                .build();
    }

    @Override
    public Company toDomain(final CompanyEntity entity) {
        if(entity==null) {
            return null;
        }

        return Company.WithIdBuilder()
                .companyId(CompanyId.of(entity.getId()))
                .companyDetails(companyDetails(entity))
                .companyGrades(companyGrades(entity))
                .registrantId(RegistrantId.of(entity.getRegistrantId()))
                .build();
    }

    public List<Company> toDomainList(final List<CompanyEntity> entityList) {
        return entityList.stream().map(this::toDomain).toList();
    }

    private CompanyDetails companyDetails(final CompanyEntity entity) {
        return CompanyDetails.WithDateBuilder()
                .name(entity.getName())
                .companyAddress(Address.of(entity.getAddress()))
                .description(entity.getDescription())
                .imgUrl(entity.getImgUrl())
                .companyStatus(entity.getCompanyStatus())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

    private CompanyGrades companyGrades(final CompanyEntity entity) {
        return CompanyGrades.WithTotalBuilder()
                .total(entity.getTotalGrade())
                .salaryAndBenefits(entity.getSalaryAndBenefits())
                .workLifeBalance(entity.getWorkLifeBalance())
                .organizationalCulture(entity.getOrganizationalCulture())
                .careerAdvancement(entity.getCareerAdvancement())
                .build();
    }

}