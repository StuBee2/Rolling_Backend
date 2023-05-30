package com.stubee.rollingapplication.domain.logging.port.spi;

import com.stubee.rollingcore.domain.logging.model.Logging;

import java.util.List;
import java.util.UUID;

public interface CommandLoggingPort {

    Logging save(Logging logging);

    List<Logging> findByMemberId(UUID memberId);

}