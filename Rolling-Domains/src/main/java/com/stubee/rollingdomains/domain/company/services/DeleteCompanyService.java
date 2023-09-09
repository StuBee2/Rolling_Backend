package com.stubee.rollingdomains.domain.company.services;

import com.stubee.rollingdomains.domain.company.services.commands.DeleteCompanyCommand;

public interface DeleteCompanyService {

    void delete(DeleteCompanyCommand command);

}