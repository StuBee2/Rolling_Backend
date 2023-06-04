package com.stubee.rollingapplication.domain.review.service;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.review.port.api.QueryReviewUseCase;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.common.dto.PageDataResponse;
import com.stubee.rollingcore.common.dto.PageDto;
import com.stubee.rollingcore.domain.review.dto.response.ReviewInfoResponse;
import com.stubee.rollingcore.domain.review.dto.response.ReviewQueryResponse;
import com.stubee.rollingcore.domain.review.exceptionn.ReviewNotFoundException;
import com.stubee.rollingcore.domain.review.model.Review;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryReviewService implements QueryReviewUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final QueryReviewPort queryReviewPort;

    @Override
    public ReviewInfoResponse getInfo(final UUID reviewId) {
        return queryReviewPort.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);
    }

    @Override
    public PageDataResponse<List<ReviewQueryResponse>> getMy(PageDto pageDto) {
        return getByMemberId(memberSecurityPort.getCurrentMember().id(), pageDto);
    }

    @Override
    public PageDataResponse<List<ReviewQueryResponse>> getByMemberId(final UUID memberId, PageDto pageDto) {
        return PageDataResponse.response(queryReviewPort.findByMemberId(memberId, pageDto));
    }

    @Override
    public PageDataResponse<List<ReviewInfoResponse>> getByCompanyId(final UUID companyId, PageDto pageDto) {
        return PageDataResponse.response(queryReviewPort.findByCompanyId(companyId, pageDto));
    }

    @Override
    public List<Review> getAll() {
        return queryReviewPort.findAll();
    }

}