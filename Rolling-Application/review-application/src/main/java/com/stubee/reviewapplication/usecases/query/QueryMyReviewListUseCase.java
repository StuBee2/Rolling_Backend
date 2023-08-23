package com.stubee.reviewapplication.usecases.query;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
import com.stubee.reviewapplication.services.query.response.ReviewQueryResponse;

import java.util.List;

public interface QueryMyReviewListUseCase {

    PageDataResponse<List<ReviewQueryResponse>> get(PageRequest pageRequest);

}