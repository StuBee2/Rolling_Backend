package com.stubee.reviewpersistence.repository;

import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.persistencecommons.entity.ReviewEntity;
import com.stubee.reviewapplication.usecases.query.response.ReviewInfoResponse;
import com.stubee.reviewapplication.usecases.query.response.ReviewQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryReviewRepository {

    ReviewEntity findById(UUID reviewId);

    ReviewInfoResponse findInfoById(UUID reviewId);

    List<ReviewQueryResponse> findByMemberId(UUID memberId, PageRequest pageRequest);

    List<ReviewInfoResponse> findByCompanyId(UUID companyId, PageRequest pageRequest);

}