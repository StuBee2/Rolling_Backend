package rolling.application.company.interactor.command;

import rolling.domain.company.model.*;
import rolling.domain.member.model.MemberId;

abstract class CompanyMapper {

    static Company toDomain(final RegisterCompanyCommand command, final MemberId memberId) {
        return Company.ExceptIdBuilder()
                .registrantId(RegistrantId.of(memberId))
                .details(new CompanyDetails(
                        command.name(),
                        command.description(),
                        Address.of(command.address(), command.addressEtc()),
                        CompanyLogo.of(command.imgUrl(), command.rgb())
                ))
                .grades(CompanyGrades.zero())
                .build();
    }

    static CompanyDetails toDetails(final ModifyCompanyDetailsCommand command) {
        return new CompanyDetails(
                command.name(),
                command.description(),
                Address.of(command.companyAddress(), command.companyAddressEtc()),
                CompanyLogo.of(command.url(), command.rgb())
        );
    }

}