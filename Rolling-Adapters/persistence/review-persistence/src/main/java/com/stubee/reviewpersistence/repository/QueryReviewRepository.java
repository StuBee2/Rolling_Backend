package com.stubee.reviewpersistence.repository;

import com.stubee.persistencecommons.commons.entity.ReviewEntity;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.reviewapplication.services.query.response.ReviewInfoResponse;
import com.stubee.reviewapplication.services.query.response.ReviewQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryReviewRepository {

    ReviewEntity findById(UUID reviewId);

    ReviewInfoResponse findInfoById(UUID reviewId);

    List<ReviewQueryResponse> findByMemberId(UUID memberId, PageRequest pageRequest);

    List<ReviewInfoResponse> findByCompanyId(UUID companyId, PageRequest pageRequest);

}