package com.stubee.rollingapplication.domain.company.service;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.api.CommandCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.CommandCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.company.dto.command.RegisterCompanyCommand;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.member.model.Member;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@CommandService
@RequiredArgsConstructor
public class CommandCompanyService implements CommandCompanyUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandCompanyPort commandCompanyPort;

    @Override
    public Company register(final RegisterCompanyCommand command) {
        Member member = memberSecurityPort.getCurrentMember();

        return commandCompanyPort.save(toDomain(command, member.id()));
    }

    private Company toDomain(final RegisterCompanyCommand command, final UUID memberId) {
        return Company.builder()
                .name(command.name())
                .address(command.address())
                .description(command.description())
                .imgUrl(command.imgUrl())
                .totalGrade(0.0)
                .balanceGrade(0.0)
                .salaryGrade(0.0)
                .welfareGrade(0.0)
                .registrantId(memberId)
                .build();
    }

}