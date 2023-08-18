package com.stubee.loggingapplication.outports;

import com.stubee.rollingdomains.domain.logging.model.Logging;

public interface CommandLoggingPort {

    Logging save(Logging logging);

}