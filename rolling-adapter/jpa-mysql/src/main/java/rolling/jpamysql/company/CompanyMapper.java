package rolling.jpamysql.company;

import rolling.domain.company.model.*;

import java.util.List;

abstract class CompanyMapper {

    static CompanyJPAEntity toEntity(final Company domain) {
        return CompanyJPAEntity.builder()
                .registrantId(domain.registrantId().getId())
                .companyStatus(domain.status())

                .name(domain.details().name())
                .address(domain.details().address().address())
                .addressEtc(domain.details().address().etc())
                .description(domain.details().description())
                .logoUrl(domain.details().logo().url())
                .logoRGB(domain.details().logo().rgb())

                .totalGrade(domain.grades().getTotal())
                .salaryAndBenefits(domain.grades().getSalaryAndBenefits())
                .workLifeBalance(domain.grades().getWorkLifeBalance())
                .organizationalCulture(domain.grades().getOrganizationalCulture())
                .careerAdvancement(domain.grades().getCareerAdvancement())
                .build();
    }

    static CompanyJPAEntity toEntityWithId(final Company domain) {
        return CompanyJPAEntity.builder()
                .id(domain.id().getId())
                .registrantId(domain.registrantId().getId())
                .companyStatus(domain.status())

                .name(domain.details().name())
                .address(domain.details().address().address())
                .addressEtc(domain.details().address().etc())
                .description(domain.details().description())
                .logoUrl(domain.details().logo().url())
                .logoRGB(domain.details().logo().rgb())

                .totalGrade(domain.grades().getTotal())
                .salaryAndBenefits(domain.grades().getSalaryAndBenefits())
                .workLifeBalance(domain.grades().getWorkLifeBalance())
                .organizationalCulture(domain.grades().getOrganizationalCulture())
                .careerAdvancement(domain.grades().getCareerAdvancement())
                
                .createdAt(domain.createdAt())
                .modifiedAt(domain.modifiedAt())
                .build();
    }

    static Company toDomain(final CompanyJPAEntity entity) {
        if(entity==null) {
            return null;
        }

        return Company.WithIdBuilder()
                .id(CompanyId.of(entity.getId()))
                .status(entity.getCompanyStatus())
                .registrantId(RegistrantId.of(entity.getRegistrantId()))
                .details(
                        new CompanyDetails(
                                entity.getName(),
                                entity.getDescription(),
                                Address.of(entity.getAddress(), entity.getAddressEtc()),
                                CompanyLogo.of(entity.getLogoUrl(), entity.getLogoRGB())
                        )
                )
                .grades(CompanyGrades.builder()
                        .total(entity.getTotalGrade())
                        .salaryAndBenefits(entity.getSalaryAndBenefits())
                        .workLifeBalance(entity.getWorkLifeBalance())
                        .organizationalCulture(entity.getOrganizationalCulture())
                        .careerAdvancement(entity.getCareerAdvancement())
                        .build()
                )
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

    static List<Company> toDomainList(final List<CompanyJPAEntity> entityList) {
        return entityList.stream().map(CompanyMapper::toDomain).toList();
    }

}