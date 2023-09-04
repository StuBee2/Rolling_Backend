package com.stubee.reviewapplication.outports.query;

import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.reviewapplication.services.query.response.ReviewInfoResponse;
import com.stubee.reviewapplication.services.query.response.ReviewQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryReviewWithPaginationPort {

    List<ReviewQueryResponse> findByMemberId(UUID memberId, PageRequest pageRequest);

    List<ReviewInfoResponse> findByCompanyId(UUID companyId, PageRequest pageRequest);

}