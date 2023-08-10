package com.stubee.rollingadapters.domain.logging.adapters;

import com.stubee.rollingcommons.commons.annotations.Adapter;
import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.rollingexternal.persistence.domain.logging.mapper.LoggingMapper;
import com.stubee.rollingexternal.persistence.domain.logging.repository.LoggingJpaRepository;
import com.stubee.rollingports.domain.logging.ports.CommandLoggingPort;
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