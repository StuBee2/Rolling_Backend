package rolling.application.company.interactor.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.company.outport.CommandCompanyPort;
import rolling.application.member.outport.MemberSessionPort;
import rolling.domain.common.model.TSID;
import rolling.domain.company.exception.DuplicatedCompanyNameException;
import rolling.domain.company.service.CompanyService;

@Component
@Transactional
@RequiredArgsConstructor
public class RegisterCompanyUseCase {

    private final CommandCompanyPort commandCompanyPort;
    private final CompanyService companyService;
    private final MemberSessionPort memberSessionPort;

    public TSID register(final RegisterCompanyCommand command) {
        if(companyService.isNameDuplicate(command.name())) {
            throw DuplicatedCompanyNameException.EXCEPTION;
        }

        return TSID.of(
                commandCompanyPort.save(
                        CompanyMapper.toDomain(
                                command,
                                memberSessionPort.currentId()
                        )
                ).id()
        );
    }

}