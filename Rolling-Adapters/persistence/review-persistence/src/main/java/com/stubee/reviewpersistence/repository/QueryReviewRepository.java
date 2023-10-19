package com.stubee.reviewpersistence.repository;

import com.stubee.reviewapplication.usecases.query.response.ReviewStatusResponse;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.persistencecommons.entity.ReviewEntity;
import com.stubee.reviewapplication.usecases.query.response.ReviewInfoResponse;
import com.stubee.reviewapplication.usecases.query.response.ReviewQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryReviewRepository {

    ReviewEntity findById(UUID reviewId);

    ReviewInfoResponse findInfoById(UUID reviewId);

    ReviewStatusResponse findStatusByMemberId(UUID memberId);

    List<ReviewQueryResponse> findByMemberId(UUID memberId, PageRequest pageRequest);

    List<ReviewInfoResponse> findByCompanyId(UUID companyId, PageRequest pageRequest);

    List<ReviewEntity> findAll(PageRequest pageRequest);

}