package com.stubee.loggingpersistence.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.loggingapplication.outports.CommandLoggingPort;
import com.stubee.rollingdomains.logging.model.HistoryLogging;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class CommandHistoryLoggingAdapter implements CommandLoggingPort<HistoryLogging> {

    private final HistoryLoggingJpaRepository historyLoggingJpaRepository;
    private final HistoryLoggingMapper historyLoggingMapper;

    @Override
    public HistoryLogging save(final HistoryLogging logging) {
        return historyLoggingMapper.toDomain(historyLoggingJpaRepository.save(historyLoggingMapper.toEntity(logging)));
    }

}