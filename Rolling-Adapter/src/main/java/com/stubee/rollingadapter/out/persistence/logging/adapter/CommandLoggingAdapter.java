package com.stubee.rollingadapter.out.persistence.logging.adapter;

import com.stubee.rollingadapter.out.persistence.logging.mapper.LoggingMapper;
import com.stubee.rollingadapter.out.persistence.logging.repository.LoggingJpaRepository;
import com.stubee.rollingapplication.domain.logging.port.spi.CommandLoggingPort;
import com.stubee.rollingcore.domain.logging.model.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommandLoggingAdapter implements CommandLoggingPort {

    private final LoggingJpaRepository loggingJpaRepository;
    private final LoggingMapper loggingMapper;

    @Override
    public Logging save(Logging logging) {
        return loggingMapper.toDomain(loggingJpaRepository.save(loggingMapper.toEntity(logging)));
    }

    @Override
    public List<Logging> findByMemberId(UUID memberId) {
        return loggingJpaRepository.findAllByMemberId(memberId)
                .stream().map(loggingMapper::toDomain).toList();
    }

}
