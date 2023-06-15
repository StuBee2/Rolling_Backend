package com.stubee.rollingapplication.domain.review.service;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.review.port.api.CommandReviewUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.CommandReviewPort;
import com.stubee.rollingcore.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.member.model.MemberId;
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
    public Review write(WriteReviewCommand command) {
        Member member = memberSecurityPort.getCurrentMember();

        if(isNotFound(command.companyId())) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        return commandReviewPort.save(createExceptReviewId(command, member.memberId()));
    }

    private boolean isNotFound(final UUID companyId) {
        return queryCompanyPort.existsByCompanyId(companyId);
    }

    private Review createExceptReviewId(WriteReviewCommand command, MemberId memberId) {
        return Review.create(command, memberId);
    }

}