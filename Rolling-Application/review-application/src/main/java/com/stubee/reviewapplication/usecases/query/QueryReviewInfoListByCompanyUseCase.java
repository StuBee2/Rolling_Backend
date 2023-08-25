package com.stubee.reviewapplication.usecases.query;

import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.reviewapplication.services.query.response.ReviewInfoResponse;

import java.util.List;
import java.util.UUID;

public interface QueryReviewInfoListByCompanyUseCase {

    PageDataResponse<List<ReviewInfoResponse>> get(UUID companyId, PageRequest pageRequest);

}