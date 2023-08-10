package com.stubee.rollingports.domain.review.ports;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingdomains.domain.review.response.ReviewInfoResponse;
import com.stubee.rollingdomains.domain.review.response.ReviewQueryResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QueryReviewPort {

    Optional<Review> findById(UUID id);

    Optional<ReviewInfoResponse> findInfoById(UUID id);

    List<ReviewQueryResponse> findByMemberId(UUID memberId, PageRequest pageRequest);

    List<ReviewInfoResponse> findByCompanyId(UUID companyId, PageRequest pageRequest);

}