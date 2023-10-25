package com.stubee.rollingdomains.domain.company.services;

import com.stubee.rollingdomains.domain.company.services.commands.ModifyCompanyStatusCommand;
import com.stubee.rollingdomains.domain.company.services.commands.ModifyCompanyDetailsCommand;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public interface ModifyCompanyService {

    void modify(ModifyCompanyDetailsCommand command, MemberId memberId);

    void modify(ModifyCompanyStatusCommand command);

}