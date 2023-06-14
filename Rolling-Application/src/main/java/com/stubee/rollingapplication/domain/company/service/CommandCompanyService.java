package com.stubee.rollingapplication.domain.company.service;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.api.CommandCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.CommandCompanyPort;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.common.exception.WrongAuthorityException;
import com.stubee.rollingcore.domain.company.dto.command.RegisterCompanyCommand;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@CommandService
@RequiredArgsConstructor
public class CommandCompanyService implements CommandCompanyUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandCompanyPort commandCompanyPort;
    private final QueryCompanyPort queryCompanyPort;

    @Override
    public Company register(RegisterCompanyCommand command) {
        Member member = memberSecurityPort.getCurrentMember();

        return commandCompanyPort.save(createExceptCompanyId(command, member.memberId()));
    }

    @Override
    public void update(Company company) {
        commandCompanyPort.update(company);
    }

    @Override
    public void delete(final UUID companyId) {
         final UUID registrantId = queryCompanyPort.findById(companyId)
                .orElse(null)
                 .registrantId();
         //리펙 필요

         if(registrantId==null) {
             throw WrongAuthorityException.EXCEPTION;
         }

         final UUID memberId = memberSecurityPort.getCurrentMember().memberId().id();

         if(!registrantId.equals(memberId)) {
             throw WrongAuthorityException.EXCEPTION;
         }

        commandCompanyPort.deleteById(CompanyId.create(companyId));
    }

    private Company createExceptCompanyId(RegisterCompanyCommand command, MemberId memberId) {
        return Company.createExceptCompanyId(command, memberId);
    }

}