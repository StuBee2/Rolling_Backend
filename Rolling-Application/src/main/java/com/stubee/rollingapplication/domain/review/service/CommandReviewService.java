package com.stubee.rollingapplication.domain.review.service;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.review.port.api.CommandReviewUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.CommandReviewPort;
import com.stubee.rollingcore.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.review.dto.command.WriteReviewCommand;
import com.stubee.rollingcore.domain.review.model.Review;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@CommandService
@RequiredArgsConstructor
public class CommandReviewService implements CommandReviewUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandReviewPort commandReviewPort;
    private final QueryCompanyPort queryCompanyPort;

    @Override
    public Review write(final WriteReviewCommand command) {
        Member member = memberSecurityPort.getCurrentMember();

        if(isNotFound(command.companyId())) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        return commandReviewPort.save(toDomain(command, member.id()));
    }

    private boolean isNotFound(final UUID companyId) {
        return queryCompanyPort.existsByCompanyId(companyId);
    }

    private Review toDomain(final WriteReviewCommand command, final UUID memberId) {
        double totalGrade = Math.round((command.balanceGrade()+command.salaryGrade()+command.welfareGrade())/3.0*10)/10.0;

        return Review.builder()
                .companyId(command.companyId())
                .content(command.content())
                .position(command.position())
                .careerPath(command.careerPath())
                .balanceGrade(command.balanceGrade())
                .salaryGrade(command.salaryGrade())
                .welfareGrade(command.welfareGrade())
                .totalGrade(totalGrade)
                .memberId(memberId)
                .build();
    }

}