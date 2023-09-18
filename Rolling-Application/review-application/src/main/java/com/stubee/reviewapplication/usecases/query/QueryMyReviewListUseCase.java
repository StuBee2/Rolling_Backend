package com.stubee.reviewapplication.usecases.query;

import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.reviewapplication.usecases.query.response.ReviewQueryResponse;

import java.util.List;

public interface QueryMyReviewListUseCase {

    PageDataResponse<List<ReviewQueryResponse>> get(PageRequest pageRequest);

}