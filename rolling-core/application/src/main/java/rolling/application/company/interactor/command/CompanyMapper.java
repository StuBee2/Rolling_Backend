package rolling.application.company.interactor.command;

import rolling.domain.company.model.*;
import rolling.domain.member.model.MemberId;

abstract class CompanyMapper {

    static Company toDomain(final RegisterCompanyCommand command, final MemberId memberId) {
        return Company.ExceptIdBuilder()
                .companyDetails(CompanyDetails.builder()
                        .registrantId(RegistrantId.of(memberId))
                        .name(command.name())
                        .description(command.description())
                        .companyAddress(Address.of(command.address(), command.addressEtc()))
                        .companyLogo(CompanyLogo.of(command.imgUrl(), command.rgb()))
                        .build())
                .companyGrades(CompanyGrades.zero())
                .build();
    }

    static CompanyDetails toDetails(final ModifyCompanyDetailsCommand command, final MemberId memberId) {
        return CompanyDetails.builder()
                .registrantId(RegistrantId.of(memberId))
                .name(command.name())
                .description(command.description())
                .companyAddress(Address.of(command.companyAddress(), command.companyAddressEtc()))
                .companyLogo(CompanyLogo.of(command.url(), command.rgb()))
                .build();
    }

}