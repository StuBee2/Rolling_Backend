package rolling.application.company.interactor.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.company.outport.CommandCompanyPort;

@Component
@Transactional
@RequiredArgsConstructor
public class DeleteCompanyUseCase {

    private final CommandCompanyPort commandCompanyPort;

    public void delete(final DeleteCompanyCommand command) {
        commandCompanyPort.deleteById(command.companyId());
    }

}