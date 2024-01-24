package rolling.domain.logging.model;

import lombok.Builder;
import rolling.domain.member.model.MemberId;

import java.time.LocalDateTime;

public record HistoryLogging(
        Long id,
        String description,
        String module,
        MemberId memberId,
        Boolean isAnonymous,
        LocalDateTime createdAt) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public HistoryLogging(String description, String module, MemberId memberId) {
        this(null, description, module, memberId, memberId==null, null);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public HistoryLogging {
    }
}