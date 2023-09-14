package com.stubee.reviewapplication.outports.query;

import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.reviewapplication.usecases.query.response.ReviewInfoResponse;
import com.stubee.reviewapplication.usecases.query.response.ReviewQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryReviewWithPaginationPort {

    List<ReviewQueryResponse> findByMemberId(UUID memberId, PageRequest pageRequest);

    List<ReviewInfoResponse> findByCompanyId(UUID companyId, PageRequest pageRequest);

    default PageDataResponse<List<ReviewQueryResponse>> getByMemberId(final UUID memberId, final PageRequest pageRequest) {
        return PageDataResponse.create(findByMemberId(memberId, pageRequest));
    }

    default PageDataResponse<List<ReviewInfoResponse>> getByCompanyId(final UUID companyId, final PageRequest pageRequest) {
        return PageDataResponse.create(findByCompanyId(companyId, pageRequest));
    }

}