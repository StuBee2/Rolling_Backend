package rolling.jpamysql.company;

import rolling.domain.company.model.*;

import java.util.List;

abstract class CompanyMapper {

    static CompanyJPAEntity toEntity(final Company domain) {
        return CompanyJPAEntity.builder()
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

    static CompanyJPAEntity toEntityWithId(final Company domain) {
        return CompanyJPAEntity.builder()
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

    static Company toDomain(final CompanyJPAEntity entity) {
        if(entity==null) {
            return null;
        }

        return Company.WithIdBuilder()
                .companyId(CompanyId.of(entity.getId()))
                .companyDetails(companyDetails(entity))
                .companyGrades(companyGrades(entity))
                .build();
    }

    static List<Company> toDomainList(final List<CompanyJPAEntity> entityList) {
        return entityList.stream().map(CompanyMapper::toDomain).toList();
    }

    private static CompanyDetails companyDetails(final CompanyJPAEntity entity) {
        return CompanyDetails.builder()
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

    private static CompanyGrades companyGrades(final CompanyJPAEntity entity) {
        return CompanyGrades.builder()
                .total(entity.getTotalGrade())
                .salaryAndBenefits(entity.getSalaryAndBenefits())
                .workLifeBalance(entity.getWorkLifeBalance())
                .organizationalCulture(entity.getOrganizationalCulture())
                .careerAdvancement(entity.getCareerAdvancement())
                .build();
    }

}