package com.stubee.rollingapplication.domain.review.port.api.query;

import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.domain.review.response.ReviewQueryResponse;

import java.util.List;

public interface QueryMyReviewListUseCase {

    PageDataResponse<List<ReviewQueryResponse>> get(PageRequest pageRequest);

}