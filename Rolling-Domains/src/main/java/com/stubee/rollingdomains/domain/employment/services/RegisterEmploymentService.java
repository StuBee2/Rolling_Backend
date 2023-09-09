package com.stubee.rollingdomains.domain.employment.services;

import com.stubee.rollingdomains.domain.employment.model.Employment;
import com.stubee.rollingdomains.domain.employment.services.commands.RegisterEmploymentCommand;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public interface RegisterEmploymentService {

    Employment register(RegisterEmploymentCommand command, MemberId memberId);

}