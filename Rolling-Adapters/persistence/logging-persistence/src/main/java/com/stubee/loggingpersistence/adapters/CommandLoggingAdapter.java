package com.stubee.loggingpersistence.adapters;

import com.stubee.loggingapplication.outports.CommandLoggingPort;
import com.stubee.persistencecommons.commons.annotations.Adapter;
import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.loggingpersistence.mapper.LoggingMapper;
import com.stubee.loggingpersistence.repository.LoggingJpaRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CommandLoggingAdapter implements CommandLoggingPort {

    private final LoggingJpaRepository loggingJpaRepository;
    private final LoggingMapper loggingMapper;

    @Override
    public Logging save(final Logging logging) {
        return loggingMapper.toDomain(loggingJpaRepository.save(loggingMapper.toEntity(logging)));
    }

}