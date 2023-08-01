package com.stubee.rollingadapter.persistence.logging.adapter;

import com.stubee.rollinginfrastructure.global.annotation.Adapter;
import com.stubee.rollingadapter.persistence.logging.mapper.LoggingMapper;
import com.stubee.rollingadapter.persistence.logging.repository.LoggingJpaRepository;
import com.stubee.rollingapplication.domain.logging.port.spi.CommandLoggingPort;
import com.stubee.rollingcore.domain.logging.model.Logging;
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