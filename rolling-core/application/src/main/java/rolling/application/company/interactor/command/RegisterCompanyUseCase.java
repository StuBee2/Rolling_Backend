package rolling.application.company.interactor.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.company.outport.CommandCompanyPort;
import rolling.application.company.outport.QueryCompanyPort;
import rolling.application.member.outport.MemberSessionPort;
import rolling.domain.common.model.TSID;
import rolling.domain.company.exception.DuplicatedCompanyNameException;
import rolling.domain.company.model.Company;
import rolling.domain.member.model.MemberId;

@Component
@Transactional
@RequiredArgsConstructor
public class RegisterCompanyUseCase {

    private final CommandCompanyPort commandCompanyPort;
    private final QueryCompanyPort queryCompanyPort;
    private final MemberSessionPort memberSessionPort;

    public TSID register(final RegisterCompanyCommand command) {
        if(queryCompanyPort.existsBy(command.name())) {
            throw DuplicatedCompanyNameException.EXCEPTION;
        }

        final MemberId memberId = memberSessionPort.currentId();
        final Company company = CompanyMapper.toDomain(command, memberId);

        return TSID.of(commandCompanyPort.save(company).companyId());
    }

}