package com.stubee.rollingapplication.domain.review.port.api;

import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.domain.review.response.ReviewQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryReviewByMemberUseCase {

    PageDataResponse<List<ReviewQueryResponse>> getMy(PageRequest pageRequest);

    PageDataResponse<List<ReviewQueryResponse>> getByMemberId(UUID memberId, PageRequest pageRequest);

}