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
                .registrantId(domain.companyDetails().registrantId().getId())
                .name(domain.companyDetails().name())
                .address(domain.companyDetails().companyAddress().address())
                .addressEtc(domain.companyDetails().companyAddress().etc())
                .description(domain.companyDetails().description())
                .logoUrl(domain.companyDetails().companyLogo().url())
                .logoRGB(domain.companyDetails().companyLogo().rgb())
                .companyStatus(domain.companyDetails().companyStatus())
                .totalGrade(domain.companyGrades().getTotal())
                .salaryAndBenefits(domain.companyGrades().getSalaryAndBenefits())
                .workLifeBalance(domain.companyGrades().getWorkLifeBalance())
                .organizationalCulture(domain.companyGrades().getOrganizationalCulture())
                .careerAdvancement(domain.companyGrades().getCareerAdvancement())
                .registrantId(domain.companyDetails().registrantId().getId())
                .build();
    }

    public CompanyEntity toEntityWithId(final Company domain) {
        return CompanyEntity.builder()
                .id(domain.companyId().getId())
                .registrantId(domain.companyDetails().registrantId().getId())
                .name(domain.companyDetails().name())
                .address(domain.companyDetails().companyAddress().address())
                .description(domain.companyDetails().description())
                .logoUrl(domain.companyDetails().companyLogo().url())
                .logoRGB(domain.companyDetails().companyLogo().rgb())
                .companyStatus(domain.companyDetails().companyStatus())
                .totalGrade(domain.companyGrades().getTotal())
                .salaryAndBenefits(domain.companyGrades().getSalaryAndBenefits())
                .workLifeBalance(domain.companyGrades().getWorkLifeBalance())
                .organizationalCulture(domain.companyGrades().getOrganizationalCulture())
                .careerAdvancement(domain.companyGrades().getCareerAdvancement())
                .createdAt(domain.companyDetails().createdAt())
                .modifiedAt(domain.companyDetails().modifiedAt())
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
                .build();
    }

    public List<Company> toDomainList(final List<CompanyEntity> entityList) {
        return entityList.stream().map(this::toDomain).toList();
    }

    private CompanyDetails companyDetails(final CompanyEntity entity) {
        return CompanyDetails.WithDateBuilder()
                .registrantId(RegistrantId.of(entity.getRegistrantId()))
                .name(entity.getName())
                .companyAddress(Address.of(entity.getAddress(), entity.getAddressEtc()))
                .description(entity.getDescription())
                .companyLogo(CompanyLogo.of(entity.getLogoUrl(), entity.getLogoRGB()))
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