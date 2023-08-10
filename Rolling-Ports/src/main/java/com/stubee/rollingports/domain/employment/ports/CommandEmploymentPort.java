package com.stubee.rollingports.domain.employment.ports;

import com.stubee.rollingdomains.domain.employment.model.Employment;

public interface CommandEmploymentPort {

    Employment register(Employment employment);

}