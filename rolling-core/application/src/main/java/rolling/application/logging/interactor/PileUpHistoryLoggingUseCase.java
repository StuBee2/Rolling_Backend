package rolling.application.logging.interactor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.logging.outport.CommandLoggingPort;
import rolling.application.logging.outport.PileUpHistoryLoggingCommand;
import rolling.domain.logging.model.HistoryLogging;

@Component
@Transactional
@RequiredArgsConstructor
public class PileUpHistoryLoggingUseCase {

    private final CommandLoggingPort<HistoryLogging> commandLoggingPort;

    public HistoryLogging pileUp(final PileUpHistoryLoggingCommand command) {
        return commandLoggingPort.save(command.toDomain());
    }

}