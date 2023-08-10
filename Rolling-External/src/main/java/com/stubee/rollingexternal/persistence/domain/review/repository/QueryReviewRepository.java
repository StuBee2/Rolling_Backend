package com.stubee.rollingexternal.persistence.domain.review.repository;

import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.domain.review.response.ReviewInfoResponse;
import com.stubee.rollingdomains.domain.review.response.ReviewQueryResponse;
import com.stubee.rollingexternal.persistence.domain.review.entity.ReviewEntity;

import java.util.List;
import java.util.UUID;

public interface QueryReviewRepository {

    ReviewEntity findById(UUID reviewId);

    ReviewInfoResponse findInfoById(UUID reviewId);

    List<ReviewQueryResponse> findByMemberId(UUID memberId, PageRequest pageRequest);

    List<ReviewInfoResponse> findByCompanyId(UUID companyId, PageRequest pageRequest);

}