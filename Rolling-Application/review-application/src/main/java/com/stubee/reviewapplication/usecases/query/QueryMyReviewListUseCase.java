package com.stubee.reviewapplication.usecases.query;

import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.reviewapplication.services.query.response.ReviewQueryResponse;

import java.util.List;

public interface QueryMyReviewListUseCase {

    PageDataResponse<List<ReviewQueryResponse>> get(PageRequest pageRequest);

}