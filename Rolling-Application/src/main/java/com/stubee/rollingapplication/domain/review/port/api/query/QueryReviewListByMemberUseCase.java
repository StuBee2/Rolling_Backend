package com.stubee.rollingapplication.domain.review.port.api.query;

import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.domain.review.response.ReviewQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryReviewListByMemberUseCase {

    PageDataResponse<List<ReviewQueryResponse>> get(UUID memberId, PageRequest pageRequest);

}