package com.stubee.rollingusecases.domain.review.usecases.query;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
import com.stubee.rollingdomains.domain.review.response.ReviewQueryResponse;

import java.util.List;

public interface QueryMyReviewListUseCase {

    PageDataResponse<List<ReviewQueryResponse>> get(PageRequest pageRequest);

}