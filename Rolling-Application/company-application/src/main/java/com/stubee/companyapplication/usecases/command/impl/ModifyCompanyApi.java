package com.stubee.companyapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.companyapplication.mapper.CompanyMapper;
import com.stubee.companyapplication.usecases.command.ModifyCompanyUseCase;
import com.stubee.rollingdomains.domain.company.model.CompanyDetails;
import com.stubee.rollingdomains.domain.company.services.ModifyCompanyService;
import com.stubee.companyapplication.usecases.command.ModifyCompanyDetailsCommand;
import com.stubee.companyapplication.usecases.command.ModifyCompanyStatusCommand;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ModifyCompanyApi implements ModifyCompanyUseCase {

    private final ModifyCompanyService modifyCompanyService;
    private final GetCurrentMemberPort getCurrentMemberPort;

    @Override
    public void modify(final ModifyCompanyDetailsCommand command) {
        final MemberId memberId = getCurrentMemberPort.getMemberId();

        final CompanyDetails companyDetails = CompanyMapper.toDetails(command, memberId);

        modifyCompanyService.modify(command.id(), companyDetails);
    }

    @Override
    public void modify(final ModifyCompanyStatusCommand command) {
        modifyCompanyService.modify(command.companyId(), command.status());
    }

}