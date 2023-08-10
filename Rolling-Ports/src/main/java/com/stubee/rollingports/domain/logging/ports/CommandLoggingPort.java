package com.stubee.rollingports.domain.logging.ports;

import com.stubee.rollingdomains.domain.logging.model.Logging;

public interface CommandLoggingPort {

    Logging save(Logging logging);

}