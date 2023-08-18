package com.stubee.reviewapplication.usecases.query;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
import com.stubee.rollingdomains.domain.review.response.ReviewQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryReviewListByMemberUseCase {

    PageDataResponse<List<ReviewQueryResponse>> get(UUID memberId, PageRequest pageRequest);

}