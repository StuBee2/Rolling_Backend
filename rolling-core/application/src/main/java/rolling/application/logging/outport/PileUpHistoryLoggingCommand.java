package rolling.application.logging.outport;

import rolling.domain.logging.model.HistoryLogging;
import rolling.domain.member.model.MemberId;

public record PileUpHistoryLoggingCommand(
        String description,
        String module,
        Long memberId) {
    public HistoryLogging toDomain() {
        return HistoryLogging.ExceptIdBuilder()
                .description(description)
                .module(module)
                .memberId(MemberId.of(memberId))
                .build();
    }
}