package rolling.jpamysql.logging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rolling.application.logging.outport.CommandLoggingPort;
import rolling.domain.logging.model.HistoryLogging;

import static rolling.jpamysql.logging.HistoryLoggingMapper.*;

@Component
@RequiredArgsConstructor
class CommandHistoryLoggingAdapter implements CommandLoggingPort<HistoryLogging> {

    private final HistoryLoggingJpaRepository historyLoggingJpaRepository;

    @Override
    public HistoryLogging save(final HistoryLogging logging) {
        return toDomain(historyLoggingJpaRepository.save(toEntity(logging)));
    }

}