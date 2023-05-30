package com.stubee.rollingapplication.domain.company.port.spi;

import com.stubee.rollingcore.domain.company.model.Company;

public interface CommandCompanyPort {

    Company save(Company companyEntity);

}