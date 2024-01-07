package rolling.jpamysql.logging;

import rolling.domain.logging.model.HistoryLogging;
import rolling.domain.member.model.MemberId;

abstract class HistoryLoggingMapper {

    static HistoryLoggingJPAEntity toEntity(final HistoryLogging domain) {
        return HistoryLoggingJPAEntity.builder()
                .description(domain.description())
                .module(domain.module())
                .memberId(domain.memberId().getId())
                .isAnonymous(domain.isAnonymous())
                .build();
    }

    static HistoryLogging toDomain(final HistoryLoggingJPAEntity entity) {
        return HistoryLogging.WithIdBuilder()
                .id(entity.getId())
                .description(entity.getDescription())
                .module(entity.getModule())
                .memberId(MemberId.of(entity.getMemberId()))
                .isAnonymous(entity.getIsAnonymous())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}