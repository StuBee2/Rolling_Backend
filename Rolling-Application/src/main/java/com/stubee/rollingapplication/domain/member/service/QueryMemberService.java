package com.stubee.rollingapplication.domain.member.service;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingapplication.domain.member.port.api.QueryMemberUseCase;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.member.port.spi.QueryMemberPort;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.member.dto.response.MemberInfoResponse;
import com.stubee.rollingcore.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.review.dto.response.ReviewQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryMemberService implements QueryMemberUseCase {

    private final MemberSecurityPort securityPort;
    private final QueryMemberPort queryMemberPort;
    private final QueryCompanyPort queryCompanyPort;
    private final QueryReviewPort queryReviewPort;

    @Override
    public Member getMy() {
        return securityPort.getCurrentMember();
    }

    @Override
    public Member getMemberById(final UUID memberId) {
        return queryMemberPort.findById(memberId)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
    }

    /**
     * @deprecated 내 정보 api 분리로 인해 삭제 에정
     * */
    @Deprecated
    @Override
    public MemberInfoResponse getMyInfo() {
        final Member member = getMy();

        return toResponse(member,
                queryCompanyPort.findByRegistrantId(member.id()),
                queryReviewPort.findByMemberId(member.id()));
    }

    /**
     * @deprecated 내 정보 api 분리로 인해 삭제 에정
     * */
    @Deprecated
    @Override
    public MemberInfoResponse getInfo(final UUID memberId) {
        final Member member = getMemberById(memberId);

        return toResponse(member,
                queryCompanyPort.findByRegistrantId(memberId),
                queryReviewPort.findByMemberId(memberId));
    }

    private MemberInfoResponse toResponse(final Member member,
                                          final List<Company> companyList,
                                          final List<ReviewQueryResponse> reviewList) {
        return MemberInfoResponse.builder()
                .member(member)
                .companyList(companyList)
                .reviewList(reviewList)
                .build();
    }

}