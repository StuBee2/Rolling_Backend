package rolling.application.company.interactor.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.company.outport.CommandCompanyPort;
import rolling.domain.company.model.Company;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class UpdateCompanyUseCase {

    private final CommandCompanyPort commandCompanyPort;

    public void update(final List<Company> companyList) {
        commandCompanyPort.updateAll(companyList);
    }

}