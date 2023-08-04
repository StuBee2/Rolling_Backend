package com.stubee.rollingapplication.domain.employment.port.spi;

import com.stubee.rollingcore.domain.employment.model.Employment;

public interface CommandEmploymentPort {

    Employment register(Employment employment);

}