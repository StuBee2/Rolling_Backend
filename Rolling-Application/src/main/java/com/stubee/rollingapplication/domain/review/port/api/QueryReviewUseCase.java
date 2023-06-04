package com.stubee.rollingapplication.domain.review.port.api;

import com.stubee.rollingcore.common.dto.PageDataResponse;
import com.stubee.rollingcore.common.dto.PageDto;
import com.stubee.rollingcore.domain.review.dto.response.ReviewInfoResponse;
import com.stubee.rollingcore.domain.review.dto.response.ReviewQueryResponse;
import com.stubee.rollingcore.domain.review.model.Review;

import java.util.List;
import java.util.UUID;

public interface QueryReviewUseCase {

    ReviewInfoResponse getInfo(UUID reviewId);

    PageDataResponse<List<ReviewQueryResponse>> getMy(PageDto pageDto);

    PageDataResponse<List<ReviewQueryResponse>> getByMemberId(UUID memberId, PageDto pageDto);

    PageDataResponse<List<ReviewInfoResponse>> getByCompanyId(UUID companyId, PageDto pageDto);

    List<Review> getAll();

}