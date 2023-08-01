package com.stubee.rollingapplication.domain.logging.port.spi;

import com.stubee.rollingcore.domain.logging.model.Logging;

public interface CommandLoggingPort {

    Logging save(Logging logging);

}