package com.stubee.employmentapplication.outports;

import com.stubee.rollingdomains.domain.employment.model.Employment;

public interface CommandEmploymentPort {

    Employment register(Employment employment);

}