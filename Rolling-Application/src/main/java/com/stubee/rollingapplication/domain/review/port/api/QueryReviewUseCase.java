package com.stubee.rollingapplication.domain.review.port.api;

import com.stubee.rollingcore.common.dto.PageDataResponse;
import com.stubee.rollingcore.common.dto.PageRequest;
import com.stubee.rollingapplication.domain.review.dto.response.ReviewInfoResponse;
import com.stubee.rollingapplication.domain.review.dto.response.ReviewQueryResponse;
import com.stubee.rollingcore.domain.review.model.Review;

import java.util.List;
import java.util.UUID;

public interface QueryReviewUseCase {

    ReviewInfoResponse getInfo(UUID reviewId);

    PageDataResponse<List<ReviewQueryResponse>> getMy(PageRequest pageRequest);

    PageDataResponse<List<ReviewQueryResponse>> getByMemberId(UUID memberId, PageRequest pageRequest);

    PageDataResponse<List<ReviewInfoResponse>> getByCompanyId(UUID companyId, PageRequest pageRequest);

    List<Review> getAll();

}