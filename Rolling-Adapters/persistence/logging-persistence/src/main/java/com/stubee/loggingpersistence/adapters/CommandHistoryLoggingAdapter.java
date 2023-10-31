package com.stubee.loggingpersistence.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.loggingapplication.outports.CommandLoggingPort;
import com.stubee.rollingdomains.domain.logging.model.HistoryLogging;
import com.stubee.loggingpersistence.mapper.HistoryLoggingMapper;
import com.stubee.loggingpersistence.repository.HistoryLoggingJpaRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CommandHistoryLoggingAdapter implements CommandLoggingPort<HistoryLogging> {

    private final HistoryLoggingJpaRepository historyLoggingJpaRepository;
    private final HistoryLoggingMapper historyLoggingMapper;

    @Override
    public HistoryLogging save(final HistoryLogging logging) {
        return historyLoggingMapper.toDomain(historyLoggingJpaRepository.save(historyLoggingMapper.toEntity(logging)));
    }

}