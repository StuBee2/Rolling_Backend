package com.stubee.rollingdomains.domain.company.services;

import com.stubee.rollingdomains.domain.company.services.commands.ChangeCompanyStatusCommand;

public interface ChangeCompanyStatusService {

    void change(ChangeCompanyStatusCommand command);

}