package com.stubee.reviewapplication.usecases.query;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
import com.stubee.reviewapplication.services.query.response.ReviewInfoResponse;

import java.util.List;
import java.util.UUID;

public interface QueryReviewInfoListByCompanyUseCase {

    PageDataResponse<List<ReviewInfoResponse>> get(UUID companyId, PageRequest pageRequest);

}