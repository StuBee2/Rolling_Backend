package com.stubee.rollingdomains.domain.company.services;

import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.services.commands.RegisterCompanyCommand;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public interface RegisterCompanyService {

    Company register(RegisterCompanyCommand command, MemberId memberId);

}