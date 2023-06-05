package com.stubee.rollingapplication.domain.review.service;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.review.port.api.CommandReviewUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.CommandReviewPort;
import com.stubee.rollingcore.common.model.Grades;
import com.stubee.rollingcore.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.review.dto.command.WriteReviewCommand;
import com.stubee.rollingcore.domain.review.model.Review;
import com.stubee.rollingcore.domain.review.model.ReviewDetails;
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
                .reviewDetails(ReviewDetails.builder()
                        .content(command.content())
                        .position(command.position())
                        .careerPath(command.careerPath())
                        .build())
                .reviewGrades(Grades.builder()
                        .balanceGrade(Double.valueOf(command.balanceGrade()))
                        .salaryGrade(Double.valueOf(command.salaryGrade()))
                        .welfareGrade(Double.valueOf(command.welfareGrade()))
                        .totalGrade(totalGrade)
                        .build())
                .memberId(memberId)
                .build();
    }

}