package rolling.application.company.interactor.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.company.outport.CommandCompanyPort;
import rolling.application.company.outport.QueryCompanyPort;
import rolling.application.member.outport.MemberSessionPort;
import rolling.domain.company.model.Company;
import rolling.domain.company.model.CompanyDetails;
import rolling.domain.company.service.CompanyService;
import rolling.domain.member.model.MemberId;

@Component
@Transactional
@RequiredArgsConstructor
public class ModifyCompanyUseCase {

    private final MemberSessionPort memberSessionPort;
    private final CommandCompanyPort commandCompanyPort;
    private final QueryCompanyPort queryCompanyPort;
    private final CompanyService companyService;

    public void modify(final ModifyCompanyDetailsCommand command) {
        final MemberId memberId = memberSessionPort.currentId();
        final CompanyDetails companyDetails = CompanyMapper.toDetails(command, memberId);
        final Company company = queryCompanyPort.getBy(command.id());

        company.modify(companyDetails, companyService);

        commandCompanyPort.save(company);
    }

    public void modify(final ModifyCompanyStatusCommand command) {
        final Company company = queryCompanyPort.getBy(command.companyId().getId());

        company.modify(command.status());

        commandCompanyPort.save(company);
    }

}