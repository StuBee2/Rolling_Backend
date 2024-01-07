package rolling.jpamysql.logging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rolling.application.logging.outport.CommandLoggingPort;
import rolling.domain.logging.model.CompanyViewLogging;

import static rolling.jpamysql.logging.CompanyViewLoggingMapper.toDomain;
import static rolling.jpamysql.logging.CompanyViewLoggingMapper.toEntity;

@Component
@RequiredArgsConstructor
class CommandCompanyViewLoggingAdapter implements CommandLoggingPort<CompanyViewLogging> {

    private final CompanyViewLoggingRepository companyViewLoggingRepository;

    @Override
    public CompanyViewLogging save(CompanyViewLogging logging) {
        return toDomain(companyViewLoggingRepository.save(toEntity(logging)));
    }

}