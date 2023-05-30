package com.stubee.rollingapplication.domain.review.port.api;

import com.stubee.rollingcore.domain.review.dto.response.ReviewInfoResponse;
import com.stubee.rollingcore.domain.review.dto.response.ReviewQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryReviewUseCase {

    ReviewInfoResponse getInfo(UUID reviewId);

    List<ReviewQueryResponse> getMy();

    List<ReviewQueryResponse> getByMemberId(UUID memberId);

}