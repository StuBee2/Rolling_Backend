package com.stubee.reviewapplication.usecases.query;

import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.reviewapplication.usecases.query.response.ReviewQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryReviewListByMemberUseCase {

    PageDataResponse<List<ReviewQueryResponse>> get(UUID memberId, PageRequest pageRequest);

}